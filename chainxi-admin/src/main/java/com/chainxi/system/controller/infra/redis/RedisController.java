package com.chainxi.system.controller.infra.redis;

import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.convert.infra.redis.RedisConvert;
import com.chainxi.system.respvo.infra.RedisMonitorRespVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Properties;


@Tag(name = "管理后台 - Redis 监控")
@RestController
@RequestMapping("/infra/redis")
@RequiredArgsConstructor
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get-monitor-info")
    @Operation(summary = "获得 Redis 监控信息")
    public ResponseResult<RedisMonitorRespVO> getRedisMonitorInfo() {
        // 获得 Redis 统计信息
        Properties info =
                stringRedisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);
        Long dbSize = stringRedisTemplate.execute(RedisServerCommands::dbSize);
        Properties commandStats = stringRedisTemplate.execute((
                RedisCallback<Properties>) connection -> connection.info("commandstats"));
        // 拼接结果返回
        if (commandStats != null) {
            return ResponseResult.success(RedisConvert.INSTANCE.build(info, dbSize, commandStats));
        } else {
            return ResponseResult.error();
        }
    }

}
