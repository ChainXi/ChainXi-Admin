package com.chainxi.system.mapper.menu;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.system.entity.menu.SysMenuDo;
import com.chainxi.system.reqvo.menu.SysMenuListReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapperX<SysMenuDo> {

    default List<SysMenuDo> selectList(SysMenuListReqVo reqVo) {
        return selectList(getBaseQueryWrapper()
                .likeIfPresent(SysMenuDo::getName, reqVo.getName())
                .eqIfPresent(SysMenuDo::getStatus, reqVo.getStatus())
                .eqIfPresent(SysMenuDo::getRootId, reqVo.getRootId())
                .orderByAsc(SysMenuDo::getSort));
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(getBaseQueryWrapper().eq(SysMenuDo::getPid, parentId));
    }

    default List<SysMenuDo> selectListByMenuIds(List<Long> menuIds) {
        return selectList(getBaseQueryWrapper().in(SysMenuDo::getId, menuIds));
    }
}
