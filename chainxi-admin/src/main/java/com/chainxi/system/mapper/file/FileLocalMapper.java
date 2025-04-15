package com.chainxi.system.mapper.file;

import com.chainxi.common.web.constant.SystemEnv;
import com.chainxi.common.web.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

/**
 * @Author : ChainXi
 * @Date : 2025/4/15 0:17
 * @Desc :
 */
@Profile({SystemEnv.DEV,SystemEnv.DEMO})
@Mapper
public interface FileLocalMapper extends BaseMapperX<DemoFile> {
}

