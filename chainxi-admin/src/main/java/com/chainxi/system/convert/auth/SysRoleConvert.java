package com.chainxi.system.convert.auth;

import com.chainxi.system.bo.AuthRoleBo;
import com.chainxi.system.entity.role.SysRoleDo;
import com.chainxi.system.reqvo.role.*;
import com.chainxi.system.respvo.role.RoleExcelVO;
import com.chainxi.system.respvo.role.RoleRespVO;
import com.chainxi.system.respvo.role.RoleSimpleRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysRoleConvert {

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRoleDo convert(RoleUpdateReqVO bean);

    RoleRespVO convert(SysRoleDo bean);

    SysRoleDo convert(RoleCreateReqVo bean);

    List<RoleSimpleRespVO> convertList02(List<SysRoleDo> list);

    List<RoleExcelVO> convertList03(List<SysRoleDo> list);

    //    SysRoleDo convert(RoleCreateReqBO bean);
    AuthRoleBo convertToAuthRole(SysRoleDo sysRoleDo);

    List<AuthRoleBo> convertToAuthRole(Collection<SysRoleDo> collection);

}
