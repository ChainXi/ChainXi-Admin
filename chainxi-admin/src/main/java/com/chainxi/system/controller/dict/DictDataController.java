package com.chainxi.system.controller.dict;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.common.web.utils.ExcelUtils;
import com.chainxi.system.convert.dict.DictDataConvert;
import com.chainxi.system.entity.dict.DictDataDo;
import com.chainxi.system.reqvo.dict.data.DictDataCreateReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataExportReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataPageReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataUpdateReqVo;
import com.chainxi.system.respvo.dict.data.DictDataExcelVo;
import com.chainxi.system.respvo.dict.data.DictDataRespVo;
import com.chainxi.system.respvo.dict.data.DictDataSimpleRespVo;
import com.chainxi.system.service.dict.DictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;

@Tag(name = "管理后台 - 字典数据")
@RestController
@RequestMapping("/sys/dict-data")
@Validated
@RequiredArgsConstructor
public class DictDataController {

    private final DictDataService dictDataService;

    @GetMapping(value = "/query")
    @Operation(summary = "/查询字典数据详细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public ResponseResult<DictDataRespVo> getDictData(@RequestParam("id") Long id) {
        return ResponseResult.success(DictDataConvert.INSTANCE.convert(dictDataService.getDictData(
                id)));
    }

    @GetMapping("/query-index-map")
    @Operation(summary = "获得全部字典数据列表", description = "一般用于管理后台缓存字典数据在本地")
    // 无需添加权限认证，因为前端全局都需要
    public ResponseResult<List<DictDataSimpleRespVo>> getIndexMapDictDataList() {
        List<DictDataDo> list = dictDataService.getDictDataList();
        return ResponseResult.success(DictDataConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/query-page")
    @Operation(summary = "/获得字典类型的分页列表")
    public ResponseResult<PageResult<DictDataRespVo>> getDictTypePage(@Valid DictDataPageReqVo reqVo) {
        return ResponseResult.success(DictDataConvert.INSTANCE.convertPage(dictDataService.getDictDataPage(
                reqVo)));
    }

    @PostMapping("/create")
    @Operation(summary = "新增字典数据")
    public ResponseResult<Long> createDictData(@Valid @RequestBody DictDataCreateReqVo reqVo) {
        Long dictDataId = dictDataService.createDictData(reqVo);
        return ResponseResult.success(dictDataId);
    }

    @PutMapping("/update")
    @Operation(summary = "修改字典数据")
    public ResponseResult<Boolean> updateDictData(@Valid @RequestBody DictDataUpdateReqVo reqVo) {
        dictDataService.updateDictData(reqVo);
        return ResponseResult.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除字典数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public ResponseResult<Boolean> deleteDictData(@RequestParam("id") Long id) {
        dictDataService.deleteDictData(id);
        return ResponseResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出字典数据")
    public void export(HttpServletResponse response,
            @Valid DictDataExportReqVo reqVo) throws IOException {
        List<DictDataDo> list = dictDataService.getDictDataList(reqVo);
        List<DictDataExcelVo> data = DictDataConvert.INSTANCE.convertList02(list);
        // 输出
        ExcelUtils.write(response, "字典数据.xls", "数据列表", DictDataExcelVo.class, data);
    }

}
