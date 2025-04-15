参考:

[# SpringBoot Cache 实现二级缓存](https://juejin.cn/post/7245838232012931132)

[# Spring基于redis动态缓存过期时间](https://juejin.cn/post/7213297003869208633)

需求:系统维度配置增加common内存缓存,需要可动态配置过期时间,此处忽略业务代码

1.RedisCache存在很多闭包属性,比较难以扩展;

2.Caffeine内部自己实现了一个CacheInterface,可以直接抽离调用;

因此DLCache通过继承RedisCache再内部引入Caffeine实现,这样底层实现做到最小改动,为后续过渡JDK17准备

RedisCache内部使用同步锁实现,就直接follow了,有需要请自行改进吧

与RedisCache不同,Caffeine实际上为弱引用直接存入容器实现,因此不必与RedisCache流程一致去做序列化

```
/**
 * @Author : ChainXi
 * @Date : 2024/5/3 18:47
 * @Desc :
 */
@Slf4j
@Getter
public class DLCache extends RedisCache {
    @Nonnull
    private final RedisCacheConfiguration cacheConfig;
    @Nullable
    private Cache<Object, Object> localCache;
    @Nonnull
    private final RedisTemplate redisTemplate;
    @Nonnull
    private final RedisCacheWriter cacheWriter;
    @Nonnull
    private final SysCacheInfoService sysCacheInfoService;

    /**
     * Create new {@link RedisCache}.
     *
     * @param name        must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     */
    public DLCache(String name,
            @Nonnull RedisCacheWriter cacheWriter,
            @Nonnull RedisCacheConfiguration cacheConfig,
            @Nonnull RedisTemplate redisTemplate,
            @Nonnull SysCacheInfoService sysCacheInfoService) {
        super(name, cacheWriter, cacheConfig);
        this.cacheConfig         = cacheConfig;
        this.cacheWriter         = cacheWriter;
        this.redisTemplate       = redisTemplate;
        this.sysCacheInfoService = sysCacheInfoService;
        syncCacheConfig();
    }

    public void syncCacheConfig() {
        SysCacheInfoBo info = sysCacheInfoService.getInfo(getName());
        if (info != null) {
            cacheConfig.entryTtl(Duration.ofMillis(info.getRemoteExpireTime()));
            if (localCache == null) {
                Integer localExpireTime = info.getLocalExpireTime();
                if (localExpireTime > -1) {
                    Caffeine<Object, Object> caffeine = Caffeine.newBuilder();
                    if (localExpireTime > 0) {
                        caffeine.expireAfterWrite(localExpireTime, TimeUnit.MILLISECONDS);
                    }
                    localCache = caffeine.build();
                }
            }
        } else {
            cacheConfig.entryTtl(Duration.ZERO);
            if (localCache != null) {
                clearLocal(null);
                localCache = null;
            }
        }
    }

    @Override
    protected Object lookup(@Nonnull Object key) {
        if (localCache != null) {
            Object value = localCache.getIfPresent(key);
            if (ObjectUtil.isNotNull(value)) {
                log.debug("DLCache local get cache, key:{}, value:{}", key, value);
                return value;
            }
        }
        return super.lookup(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(@Nonnull Object key, @Nonnull Callable<T> valueLoader) {
        T val = (T) lookup(key);
        if (ObjectUtil.isNotNull(val)) {
            return val;
        }
        synchronized (this) {
            val = (T) lookup(key);
            if (ObjectUtil.isNotNull(val)) {
                return val;
            }
            try {
                val = valueLoader.call();
                put(key, val);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return val;
        }
    }

    @Override
    public void put(@Nonnull Object key, Object value) {
        Object cacheValue = preProcessCacheValue(value);
        String name = getName();
        String cacheKey = createCacheKey(key);
        if (cacheValue == null) {
            throw new IllegalArgumentException(String.format(
                    "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable" +
                            "(unless="#result == null")' or configure RedisCache to allow " +
                            "'null' via RedisCacheConfiguration.",
                    name));
        }
        cacheWriter.put(name,
                serializeCacheKey(cacheKey),
                serializeCacheValue(cacheValue),
                cacheConfig.getTtl());

        if (localCache != null) {
            localCache.put(cacheKey, cacheValue);
            sendDataSyncMsg(cacheKey);
        }
    }

    @Override
    public void evict(@Nonnull Object key) {
        // 先清理 redis 再清理 caffeine
        super.evict(key);
        clearLocal(key);
        sendDataSyncMsg(key);
    }

    @Override
    public void clear() {
        super.clear();
        clearLocal(null);
        sendDataSyncMsg(null);
    }

    private void sendDataSyncMsg(Object key) {
        redisTemplate.convertAndSend(RedisMessageConstants.SYNC_TOPIC,
                new DLCacheRefreshMsg(DLCacheRefreshMsg.Flag.DATA,
                        getName(),
                        key,
                        RedisMessageConstants.DEVICE_ID));
    }

    public void clearLocal(Object key) {
        if (localCache != null) {
            if (ObjectUtil.isNull(key)) {
                localCache.invalidateAll();
            } else {
                localCache.invalidate(key);
            }
        }
    }
}
```

```

/**
 * @Author : ChainXi
 * @Date: 2024/5/3 18:47
 * @Desc:
 */
@Configuration
@EnableCaching
public class DLCacheConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory,
            RedisCacheConfiguration redisCacheConfiguration,
            RedisTemplate redisTemplate,
            SysCacheInfoService sysCacheInfoService) {
        return new DLCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory),
                redisCacheConfiguration,
                redisTemplate,
                sysCacheInfoService);
    }
}
```

```

/**
 * @Author : ChainXi
 * @Date: 2024/5/3 18:47
 * @Desc:
 */
@Slf4j
public class DLCacheManager extends RedisCacheManager {
    private final RedisCacheWriter cacheWriter;
    private final RedisTemplate redisTemplate;
    private final SysCacheInfoService sysCacheInfoService;

    public DLCacheManager(RedisCacheWriter cacheWriter,
            RedisCacheConfiguration defaultCacheConfiguration,
            RedisTemplate redisTemplate, SysCacheInfoService sysCacheInfoService) {
        super(cacheWriter, defaultCacheConfiguration);
        this.cacheWriter   = cacheWriter;
        this.redisTemplate = redisTemplate;
        this.sysCacheInfoService = sysCacheInfoService;
    }

    @Nonnull
    @Override
    protected RedisCache createRedisCache(@Nonnull String name,
            RedisCacheConfiguration cacheConfig) {
        return new DLCache(name, cacheWriter, cacheConfig, redisTemplate,sysCacheInfoService);
    }

}
```

```

/**
 * @Author : ChainXi
 * @Date: 2024/5/3 18:47
 * @Desc:
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class DLCacheRefreshListener implements MessageListener, InitializingBean {
    private final DLCacheManager dlCacheManager;
    private final RedisMessageListenerContainer listenerContainer;
    private final RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        DLCacheRefreshMsg refreshMsg = (DLCacheRefreshMsg) redisTemplate
                .getValueSerializer()
                .deserialize(message.getBody());
        if (refreshMsg == null ||
                refreshMsg.getDeviceId().equals(RedisMessageConstants.DEVICE_ID)) {
            return;
        }
        log.debug("DLCache refresh local, cache flag:{} name:{}, key:{}",
                refreshMsg.getFlag(),
                refreshMsg.getCacheName(),
                refreshMsg.getKey());
        DLCache cache = (DLCache) dlCacheManager.getCache(refreshMsg.getCacheName());
        if (cache != null) {
            if (refreshMsg.getFlag().equals(DLCacheRefreshMsg.Flag.DATA)) {
                cache.clearLocal(refreshMsg.getKey());
            } else if (refreshMsg.getFlag().equals(DLCacheRefreshMsg.Flag.CONFIG)) {
                cache.syncCacheConfig();
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        listenerContainer.addMessageListener(this,
                new ChannelTopic(RedisMessageConstants.SYNC_TOPIC));
    }
}
```

```

/**
 * @Author : ChainXi
 * @Date: 2024/5/3 18:47
 * @Desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DLCacheRefreshMsg {
    enum Flag {DATA, CONFIG}

    private Flag flag;
    private String cacheName;
    private Object key;
    private String deviceId;
}
```

```
/**
 * @Author : ChainXi
 * @Date : 2024/5/3 18:55
 * @Desc :
 */
public class RedisMessageConstants {
    public static final String DEVICE_ID = java.util.UUID.randomUUID().toString();
    public static final String SYNC_TOPIC = "cache:dl:refresh:topic";
}
```