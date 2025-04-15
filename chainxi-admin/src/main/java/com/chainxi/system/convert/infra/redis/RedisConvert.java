package com.chainxi.system.convert.infra.redis;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.chainxi.system.respvo.infra.RedisMonitorRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Properties;

@Mapper
public interface RedisConvert {

    RedisConvert INSTANCE = Mappers.getMapper(RedisConvert.class);

    default RedisMonitorRespVO build(Properties info, Long dbSize, Properties commandStats) {
        RedisMonitorRespVO respVO = new RedisMonitorRespVO()
                .setInfo(info)
                .setDbSize(dbSize)
                .setCommandStats(new ArrayList<>(commandStats.size()));
        commandStats.forEach((key, value) -> {
            respVO
                    .getCommandStats()
                    .add(new RedisMonitorRespVO.CommandStat()
                            .setCommand(StrUtil.subAfter((String) key, "cmdstat_", false))
                            .setCalls(Long.valueOf(StrUtil.subBetween((String) value,
                                    "calls=",
                                    StrPool.COMMA)))
                            .setUsec(Long.valueOf(StrUtil.subBetween((String) value,
                                    "usec=",
                                    StrPool.COMMA))));
        });
        return respVO;
    }

}
