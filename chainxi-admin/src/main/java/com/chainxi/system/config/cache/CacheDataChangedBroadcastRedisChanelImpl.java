package com.chainxi.system.config.cache;

import com.chainxi.system.constants.system.RedisMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import jakarta.annotation.Nonnull;

/**
 * @Author : ChainXi
 * @Date : 2024/5/9 2:14
 * @Desc :
 */
@RequiredArgsConstructor
public class CacheDataChangedBroadcastRedisChanelImpl implements CacheDataChangedBroadcast {
    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void syncData(@Nonnull String cacheName, Object key) {
        redisTemplate.convertAndSend(RedisMessageConstants.SYNC_TOPIC,
                new CacheRefreshMsg(CacheRefreshMsg.Flag.DATA,
                        cacheName,
                        key,
                        RedisMessageConstants.DEVICE_ID));
    }
}
