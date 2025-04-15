package com.chainxi.system.service.menu;


import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.bo.RequestMappingBo;
import com.chainxi.system.constants.system.CacheKeyConstants;
import com.chainxi.system.entity.menu.SysResourceDo;
import com.chainxi.system.reqvo.menu.SysResourcePageReqVo;
import com.chainxi.system.reqvo.menu.SysResourceUpdateReqVo;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

/**
 * 后台资源管理Service
 * Created by macro on 2020/2/2.
 */
public interface ResourceService {
    /**
     * 添加资源
     */
    int createResource(SysResourceUpdateReqVo reqVo);

    /**
     * 修改资源
     */
    int updateResource(SysResourceUpdateReqVo reqVo);

    /**
     * 获取资源详情
     */
    SysResourceDo queryById(Long id);

    /**
     * 删除资源
     */
    int deleteResourceById(Long id);

    /**
     * 分页查询资源
     */
    PageResult<SysResourceDo> selectPage(SysResourcePageReqVo reqVo);

    /**
     * 查询全部资源
     */
    List<SysResourceDo> selectAll();

    @CachePut(CacheKeyConstants.ROLE_RES)
    RequestMappingBo refreshRequestMapping();

    /**
     * 初始化资源角色规则
     */
    RequestMappingBo getResourceRolesMap();
}
