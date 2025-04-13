package com.chainxi.common.web.config;

import lombok.*;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(
        prefix = "redisson"
)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RedissonCustomerProperties {
    private int threads;
    private int nettyThreads;
    private SingleServerConfig singleServerConfig;
    private ClusterServersConfig clusterServersConfig;
    private List<CacheGroup> cacheGroup;
    private int dnsMonitoringInterval = -1;


    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CacheGroup {
        private String groupId;
        private long ttl;
        private long maxIdleTime;
        private int maxSize;
    }

    /**
     * 集群服务器配置
     *
     * @author cong.zhen
     * @date 2023/01/18
     */
    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClusterServersConfig {
        private String clientName;
        private int masterConnectionMinimumIdleSize;
        private int masterConnectionPoolSize;
        private int slaveConnectionMinimumIdleSize;
        private int slaveConnectionPoolSize;
        private int idleConnectionTimeout;
        private int timeout;
        private int subscriptionConnectionPoolSize;
        private ReadMode readMode;
        private SubscriptionMode subscriptionMode;
    }

    /**
     * 单机模式配置
     *
     * @author cong.zhen
     * @date 2023/01/18
     */
    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SingleServerConfig {
        private String clientName;
        private int connectionMinimumIdleSize;
        private int connectionPoolSize;
        private int idleConnectionTimeout;
        private int timeout;
        private int subscriptionConnectionPoolSize;
    }
}