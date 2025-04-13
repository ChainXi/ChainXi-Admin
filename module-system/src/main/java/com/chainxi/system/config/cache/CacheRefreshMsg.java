package com.chainxi.system.config.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author : ChainXi
 * @Date : 2024/5/3 18:47
 * @Desc :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheRefreshMsg implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;

    public enum Flag {
        DATA, CONFIG;
    }

    private Flag flag;
    private String cacheName;
    private Object key;
    private String deviceId;
}
