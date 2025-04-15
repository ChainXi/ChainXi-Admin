package com.chainxi.system.convert.dict;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.bo.DictDataBo;
import com.chainxi.system.entity.dict.DictDataDo;
import com.chainxi.system.reqvo.dict.data.*;
import com.chainxi.system.respvo.dict.data.DictDataExcelVo;
import com.chainxi.system.respvo.dict.data.DictDataRespVo;
import com.chainxi.system.respvo.dict.data.DictDataSimpleRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    List<DictDataSimpleRespVo> convertList(List<DictDataDo> list);

    DictDataRespVo convert(DictDataDo bean);

    PageResult<DictDataRespVo> convertPage(PageResult<DictDataDo> page);

    DictDataDo convert(DictDataUpdateReqVo bean);

    DictDataDo convert(DictDataCreateReqVo bean);

    List<DictDataExcelVo> convertList02(List<DictDataDo> bean);

    DictDataBo convert02(DictDataDo bean);

}
