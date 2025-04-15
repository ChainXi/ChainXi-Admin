package com.chainxi.system.controller.cache;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.system.entity.cache.SysCacheInfoDo;
import com.chainxi.system.reqvo.cache.CacheInfoPageReqVo;
import com.chainxi.system.reqvo.cache.CacheInfoUpdateReqVo;
import com.chainxi.system.service.cache.SysCacheInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "管理后台 - 缓存管理")
@RestController
@RequestMapping("/sys/cache-info")
@RequiredArgsConstructor
public class CacheInfoController {
    private final SysCacheInfoService cacheInfoService;

    @GetMapping("/query")
    public ResponseResult<SysCacheInfoDo> select(@RequestParam("name") String name) {
        return ResponseResult.success(cacheInfoService.selectOne(name));
    }

    @GetMapping("/query-page")
    public ResponseResult<PageResult<SysCacheInfoDo>> selectPage(CacheInfoPageReqVo reqVo) {
        return ResponseResult.success(cacheInfoService.selectPage(reqVo));
    }

    @PutMapping("/create")
    public void createCacheInfo(@RequestBody CacheInfoUpdateReqVo reqVo) {
        cacheInfoService.createCacheInfo(reqVo);
    }

    @PostMapping("/update")
    public void updateCacheInfo(@RequestBody CacheInfoUpdateReqVo reqVo) {
        cacheInfoService.updateCacheInfo(reqVo);
    }

    @DeleteMapping("/reset-cache")
    public void clearCache(@RequestParam("name") String name) {
        cacheInfoService.clearCache(name);
    }

    @DeleteMapping("/delete-cache-info")
    public void deleteCacheInfoByKey(@RequestParam("name") String name) {
        cacheInfoService.deleteCacheInfo(name);
    }
}
