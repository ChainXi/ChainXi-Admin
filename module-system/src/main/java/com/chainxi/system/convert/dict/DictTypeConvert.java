package com.chainxi.system.convert.dict;


import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.dict.DictTypeDo;
import com.chainxi.system.reqvo.dict.type.*;
import com.chainxi.system.respvo.dict.type.DictTypeExcelVO;
import com.chainxi.system.respvo.dict.type.DictTypeRespVo;
import com.chainxi.system.respvo.dict.type.DictTypeSimpleRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictTypeConvert {

    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    PageResult<DictTypeRespVo> convertPage(PageResult<DictTypeDo> bean);

    DictTypeRespVo convert(DictTypeDo bean);

    DictTypeDo convert(DictTypeCreateReqVo bean);

    DictTypeDo convert(DictTypeUpdateReqVo bean);

    List<DictTypeSimpleRespVo> convertList(List<DictTypeDo> list);

    List<DictTypeExcelVO> convertList02(List<DictTypeDo> list);

}
