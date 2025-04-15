package com.chainxi.system.controller.dict;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.chainxi.common.web.utils.ExcelUtils;
import com.chainxi.system.convert.dict.DictTypeConvert;
import com.chainxi.system.entity.dict.DictTypeDo;
import com.chainxi.system.reqvo.dict.type.DictTypeCreateReqVo;
import com.chainxi.system.reqvo.dict.type.DictTypeExportReqVo;
import com.chainxi.system.reqvo.dict.type.DictTypeUpdateReqVo;
import com.chainxi.system.respvo.dict.type.DictTypeExcelVO;
import com.chainxi.system.respvo.dict.type.DictTypePageReqVo;
import com.chainxi.system.respvo.dict.type.DictTypeRespVo;
import com.chainxi.system.respvo.dict.type.DictTypeSimpleRespVo;
import com.chainxi.system.service.dict.DictTypeService;
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


@Tag(name = "管理后台 - 字典类型")
@RestController
@RequestMapping("/sys/dict-type")
@Validated
@RequiredArgsConstructor
public class DictTypeController {

    private final DictTypeService dictTypeService;

    @Operation(summary = "/查询字典类型详细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @GetMapping(value = "/query")
    public ResponseResult<DictTypeRespVo> getDictType(@RequestParam("id") Long id) {
        return ResponseResult.success(DictTypeConvert.INSTANCE.convert(dictTypeService.getDictType(
                id)));
    }

    @Operation(summary = "/获得字典类型的分页列表")
    @GetMapping("/query-page")
    public ResponseResult<PageResult<DictTypeRespVo>> pageDictTypes(@Valid DictTypePageReqVo reqVo) {
        return ResponseResult.success(DictTypeConvert.INSTANCE.convertPage(dictTypeService.getDictTypePage(
                reqVo)));
    }

    @GetMapping("/query-index-map")
    @Operation(summary = "获得全部字典类型列表", description = "包括开启 + 禁用的字典类型，主要用于前端的下拉选项")
    // 无需添加权限认证，因为前端全局都需要
    public ResponseResult<List<DictTypeSimpleRespVo>> getIndexMapDictTypeList() {
        List<DictTypeDo> list = dictTypeService.getDictTypeList();
        return ResponseResult.success(DictTypeConvert.INSTANCE.convertList(list));
    }

    @PostMapping("/create")
    @Operation(summary = "创建字典类型")
    public ResponseResult<Long> createDictType(@Valid @RequestBody DictTypeCreateReqVo reqVo) {
        Long dictTypeId = dictTypeService.createDictType(reqVo);
        return ResponseResult.success(dictTypeId);
    }

    @PutMapping("/update")
    @Operation(summary = "修改字典类型")
    public ResponseResult<Boolean> updateDictType(@Valid @RequestBody DictTypeUpdateReqVo reqVo) {
        dictTypeService.updateDictType(reqVo);
        return ResponseResult.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除字典类型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public ResponseResult<Boolean> deleteDictType(@RequestParam("id") Long id) {
        dictTypeService.deleteDictType(id);
        return ResponseResult.success(true);
    }

    @Operation(summary = "导出数据类型")
    @GetMapping("/export")
    public void export(HttpServletResponse response,
            @Valid DictTypeExportReqVo reqVo) throws IOException {
        List<DictTypeDo> list = dictTypeService.getDictTypeList(reqVo);
        List<DictTypeExcelVO> data = DictTypeConvert.INSTANCE.convertList02(list);
        // 输出
        ExcelUtils.write(response, "excel.xls", "类型列表", DictTypeExcelVO.class, data);
    }

}
