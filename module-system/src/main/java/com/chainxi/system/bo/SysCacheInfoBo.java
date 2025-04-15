package com.chainxi.system.bo;

import lombok.Data;

import java.util.List;

/**
 * @Author : ChainXi
 * @Date : 2024/5/3 21:35
 * @Desc :
 */
@Data
public class SysCacheInfoBo {
    /**
     * 键值
     */
    private String name;
    /**
     * 缓存过期时间
     */
    private List<Integer> expireTimes;
}
