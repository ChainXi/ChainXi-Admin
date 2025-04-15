package com.chainxi.system.mapper.dict;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.dict.DictTypeDo;
import com.chainxi.system.reqvo.dict.type.DictTypeExportReqVo;
import com.chainxi.system.respvo.dict.type.DictTypePageReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DictTypeMapper extends BaseMapperX<DictTypeDo> {

    default PageResult<DictTypeDo> selectPage(DictTypePageReqVo reqVo) {
        return selectPage(reqVo,
                getBaseQueryWrapper().likeIfPresent(DictTypeDo::getName, reqVo.getName())
                        .likeIfPresent(DictTypeDo::getType, reqVo.getType())
                        .eqIfPresent(DictTypeDo::getStatus, reqVo.getStatus())
                        .betweenIfPresent(DictTypeDo::getCreateTime, reqVo.getCreateTime())
                        .orderByDesc(DictTypeDo::getId));
    }

    default List<DictTypeDo> selectList(DictTypeExportReqVo reqVo) {
        return selectList(getBaseQueryWrapper().likeIfPresent(DictTypeDo::getName, reqVo.getName())
                .likeIfPresent(DictTypeDo::getType, reqVo.getType())
                .eqIfPresent(DictTypeDo::getStatus, reqVo.getStatus())
                .betweenIfPresent(DictTypeDo::getCreateTime, reqVo.getCreateTime()));
    }

    default DictTypeDo selectByType(String type) {
        return selectOne(getBaseQueryWrapper().eq(DictTypeDo::getType, type));
    }

    default DictTypeDo selectByName(String name) {
        return selectOne(getBaseQueryWrapper().eq(DictTypeDo::getName, name));
    }


    @Update("UPDATE sys_dict_type SET deleted = 1, deleted_time = #{deletedTime} WHERE id = #{id}")
    void updateToDelete(@Param("id") Long id, @Param("deletedTime") LocalDateTime deletedTime);
}
