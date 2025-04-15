/**
 * Copyright (c) 2013-2022 Nikita Koksharov
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chainxi.common.web.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisOperations;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;

@Data
@Slf4j
@AutoConfiguration
@ConditionalOnClass({Redisson.class, RedisOperations.class})
@AutoConfigureBefore(RedisAutoConfiguration.class)
@EnableConfigurationProperties({RedissonProperties.class, RedissonCustomerProperties.class,
        RedisProperties.class})
public class RedissonCustomerAutoConfiguration {
    private static final String REDIS_PROTOCOL_PREFIX = "redis://";
    private static final String REDISS_PROTOCOL_PREFIX = "rediss://";


    private final List<RedissonAutoConfigurationCustomizer> redissonAutoConfigurationCustomizers;

    private final RedissonProperties redissonProperties;

    private final RedisProperties redisProperties;

    private final RedissonCustomerProperties redissonCustomerProperties;

    private final ApplicationContext ctx;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        Config config = getConfig();
        return Redisson.create(config);
    }

    private Config getConfig() {
        Config config;
        Duration timeoutValue = redisProperties.getTimeout();
        int timeout = timeoutValue != null ? Math.toIntExact(timeoutValue.toMillis()) : 10000;
        String username = redisProperties.getUsername();

        if (redissonProperties.getConfig() != null) {
            try {
                config = Config.fromYAML(redissonProperties.getConfig());
            } catch (IOException e) {
                try {
                    config = Config.fromJSON(redissonProperties.getConfig());
                } catch (IOException e1) {
                    e1.addSuppressed(e);
                    throw new IllegalArgumentException("Can't parse config", e1);
                }
            }
        } else if (redissonProperties.getFile() != null) {
            try {
                InputStream is = getConfigStream();
                config = Config.fromYAML(is);
            } catch (IOException e) {
                // trying next format
                try {
                    InputStream is = getConfigStream();
                    config = Config.fromJSON(is);
                } catch (IOException e1) {
                    e1.addSuppressed(e);
                    throw new IllegalArgumentException("Can't parse config", e1);
                }
            }
        } else if (redisProperties.getSentinel() != null) {
            String[] nodes = redisProperties.getSentinel().getNodes().toArray(new String[0]);

            config = new Config();
            config
                    .useSentinelServers()
                    .setMasterName(redisProperties.getSentinel().getMaster())
                    .addSentinelAddress(nodes)
                    .setDatabase(redisProperties.getDatabase())
                    .setConnectTimeout(timeout)
                    .setUsername(username)
                    .setPassword(redisProperties.getPassword());
        } else if (redisProperties.getCluster() != null) {
            String[] nodes = redisProperties.getCluster().getNodes().toArray(new String[0]);
            config = new Config();
            config
                    .useClusterServers()
                    .addNodeAddress(nodes)
                    .setConnectTimeout(timeout)
                    .setUsername(username)
                    .setPassword(redisProperties.getPassword())
                    .setDnsMonitoringInterval(redissonCustomerProperties.getDnsMonitoringInterval());
        } else {
            config = new Config();
            String prefix = REDIS_PROTOCOL_PREFIX;
            if (redisProperties.getSsl().isEnabled()) {
                prefix = REDISS_PROTOCOL_PREFIX;
            }
            config
                    .useSingleServer()
                    .setConnectionPoolSize(1)//连接池最大容量
                    .setConnectionMinimumIdleSize(1)//最小空闲连接数
                    .setAddress(
                            prefix + redisProperties.getHost() + ":" + redisProperties.getPort())
                    .setConnectTimeout(timeout)
                    .setDatabase(redisProperties.getDatabase())
                    .setUsername(username)
                    .setPassword(redisProperties.getPassword())
                    .setDnsMonitoringInterval(redissonCustomerProperties.getDnsMonitoringInterval());
        }
        if (redissonAutoConfigurationCustomizers != null) {
            for (RedissonAutoConfigurationCustomizer customizer :
                    redissonAutoConfigurationCustomizers) {
                customizer.customize(config);
            }
        }

        config.setLazyInitialization(Boolean.TRUE);
        return config;
    }

    private InputStream getConfigStream() throws IOException {
        Resource resource = ctx.getResource(redissonProperties.getFile());
        return resource.getInputStream();
    }

}
