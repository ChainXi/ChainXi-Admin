package com.chainxi.system.mapper.datapermission;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.system.entity.datapermission.DataPermissionDeptMatcherDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataPermissionDeptMatcherMapper extends BaseMapperX<DataPermissionDeptMatcherDo> {

    default List<DataPermissionDeptMatcherDo> selectListByUserId(Long userId){
        return selectList(getBaseQueryWrapper().eq(DataPermissionDeptMatcherDo::getUserId,userId));
    }

    default int deleteByUserId(Long userId){
        return delete(getBaseQueryWrapper().eq(DataPermissionDeptMatcherDo::getUserId,userId));
    }
}
