package com.chainxi.system.convert.auth;

import com.chainxi.system.entity.menu.SysResourceDo;
import com.chainxi.system.reqvo.menu.SysResourceUpdateReqVo;
import com.chainxi.system.respvo.menu.SysResourceRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysResourceConvert {

    SysResourceConvert INSTANCE = Mappers.getMapper(SysResourceConvert.class);

    SysResourceDo convertToDo(SysResourceUpdateReqVo reqVo);

    SysResourceRespVo convertToRespVo(SysResourceDo sysResourceDo);

}
