package com.chainxi.system.mapper.menu;


import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.menu.SysResourceDo;
import com.chainxi.system.reqvo.menu.SysResourcePageReqVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysResourceMapper extends BaseMapperX<SysResourceDo> {

    default PageResult<SysResourceDo> selectPage(SysResourcePageReqVo reqVo) {
        return selectPage(reqVo,
                getBaseQueryWrapper()
                        .likeIfPresent(SysResourceDo::getName, reqVo.getName())
                        .likeIfPresent(SysResourceDo::getPatterns, reqVo.getPatterns())
                        .likeIfPresent(SysResourceDo::getCategoryId, reqVo.getCategoryId())
                        .eqIfPresent(SysResourceDo::getMappingType, reqVo.getMappingType())
                        .eqIfPresent(SysResourceDo::getAccessType, reqVo.getAccessType()));
    }
}