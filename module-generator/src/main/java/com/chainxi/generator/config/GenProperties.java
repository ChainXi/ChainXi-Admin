package com.chainxi.generator.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author : ChainXi
 * @Date : 2025/2/16 21:59
 * @Desc :
 */
@ConfigurationProperties(prefix = "gen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenProperties {
    /** 作者 */
//    @Value("${author}")
    public String author;

    /** 生成包路径 */
    public String packageName;

    /** 自动去除表前缀 */
    public Boolean autoRemovePre;

    /** 表前缀 */
    public String tablePrefix;

    /** 是否允许生成文件覆盖到本地（自定义路径） */
    public Boolean allowOverwrite;

}
