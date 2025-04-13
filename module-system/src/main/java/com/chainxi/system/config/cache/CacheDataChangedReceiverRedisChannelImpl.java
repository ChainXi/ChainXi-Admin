package com.chainxi.system.config.cache;

import com.chainxi.system.constants.system.RedisMessageConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import jakarta.annotation.Nonnull;
import java.util.Optional;

/**
 * @Author : ChainXi
 * @Date : 2024/5/10 6:01
 * @Desc :
 */
@Slf4j
@RequiredArgsConstructor
public class CacheDataChangedReceiverRedisChannelImpl implements MessageListener,
        InitializingBean, CacheDataChangedReceiver {
    private final RedisTemplate<Object, Object> redisTemplate;
    private final RedisMessageListenerContainer listenerContainer;
    private final MultiLevelCacheManager cacheManager;

    @Override
    public void onGlobalDataChanged(String name, Object key) {
        Optional.ofNullable(cacheManager.getCache(name)).ifPresent(cache -> cache.evictLocal(key));
    }

    @Override
    public void onConfigChanged(String name) {
        Optional.ofNullable(cacheManager.getCache(name)).ifPresent(MultiLevelCache::syncConfig);
    }

    @Override
    public void onMessage(@Nonnull Message message, byte[] pattern) {
        CacheRefreshMsg refreshMsg =
                (CacheRefreshMsg) redisTemplate.getValueSerializer().deserialize(message.getBody());
        if (refreshMsg == null ||
                RedisMessageConstants.DEVICE_ID.equals(refreshMsg.getDeviceId())) {
            return;
        }
        log.debug("refresh local, cache flag:{} name:{}, key:{}",
                refreshMsg.getFlag(),
                refreshMsg.getCacheName(),
                refreshMsg.getKey());
        if (refreshMsg.getFlag().equals(CacheRefreshMsg.Flag.DATA)) {
            onGlobalDataChanged(refreshMsg.getCacheName(), refreshMsg.getKey());
        } else if (refreshMsg.getFlag().equals(CacheRefreshMsg.Flag.CONFIG)) {
            onConfigChanged(refreshMsg.getCacheName());
        }
    }

    @Override
    public void afterPropertiesSet() {
        listenerContainer.addMessageListener(this,
                new ChannelTopic(RedisMessageConstants.SYNC_TOPIC));
    }
}
