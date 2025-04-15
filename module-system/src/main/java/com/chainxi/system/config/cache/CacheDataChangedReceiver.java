package com.chainxi.system.config.cache;

/**
 * @Author : ChainXi
 * @Date : 2024/5/12 0:14
 * @Desc :
 */
public interface CacheDataChangedReceiver {
    void onGlobalDataChanged(String name, Object key);

    void onConfigChanged(String name);
}
