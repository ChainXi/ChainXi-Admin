package com.chainxi.system.constants.system;

import cn.hutool.core.lang.UUID;

/**
 * @Author : ChainXi
 * @Date : 2024/5/3 18:55
 * @Desc :
 */
public class RedisMessageConstants {
    public static final String DEVICE_ID = UUID.randomUUID().toString(true);
    public static final String SYNC_TOPIC = "cache:dl:refresh:topic";
}
