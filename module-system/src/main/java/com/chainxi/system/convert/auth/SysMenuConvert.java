package com.chainxi.system.convert.auth;

import com.chainxi.system.entity.role.SysRoleMenuDo;
import com.chainxi.system.reqvo.menu.SysMenuUpdateReqVo;
import com.chainxi.system.reqvo.auth.AssignMenu;
import com.chainxi.system.respvo.menu.SysMenuRespVo;
import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.respvo.menu.SysMenuSimpleRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysMenuConvert {

    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    SysMenuDo convertToMenuDo(SysMenuUpdateReqVo sysMenuRespVo);

    List<SysMenuRespVo> convertToMenuDtos(Collection<SysMenuDo> dos);

    List<SysMenuSimpleRespVo> convertToSimpleList(Collection<SysMenuDo> dos);

    SysMenuRespVo convertToMenuDto(SysMenuDo entity);

    AssignMenu roleMenuDoToAssignMenu(SysRoleMenuDo sysRoleMenuDo);
}
