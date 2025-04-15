package com.chainxi.system.mapper.dict;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.dict.DictDataDo;
import com.chainxi.system.reqvo.dict.data.DictDataExportReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataPageReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapperX<DictDataDo> {
    default List<DictDataDo> selectEnableList() {
        return selectList(getBaseQueryWrapper().eq(DictDataDo::getStatus,
                CommonStatusEnum.ENABLE.getStatus()));
    }

    default DictDataDo selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(getBaseQueryWrapper().eq(DictDataDo::getDictType, dictType)
                .eq(DictDataDo::getValue, value));
    }

    default DictDataDo selectByDictTypeAndLabel(String dictType, String label) {
        return selectOne(getBaseQueryWrapper().eq(DictDataDo::getDictType, dictType)
                .eq(DictDataDo::getLabel, label));
    }

    default List<DictDataDo> selectByDictTypeAndValues(String dictType, Collection<String> values) {
        return selectList(new LambdaQueryWrapper<DictDataDo>().eq(DictDataDo::getDictType, dictType)
                .in(DictDataDo::getValue, values));
    }

    default long selectCountByDictType(String dictType) {
        return selectCount(getBaseQueryWrapper().eq(DictDataDo::getDictType, dictType));
    }

    default PageResult<DictDataDo> selectPage(DictDataPageReqVo reqVo) {
        return selectPage(reqVo,
                getBaseQueryWrapper().likeIfPresent(DictDataDo::getLabel, reqVo.getLabel())
                        .eqIfPresent(DictDataDo::getDictType, reqVo.getDictType())
                        .eqIfPresent(DictDataDo::getStatus, reqVo.getStatus())
                        .orderByDesc(Arrays.asList(DictDataDo::getDictType, DictDataDo::getSort)));
    }

    default List<DictDataDo> selectList(DictDataExportReqVo reqVo) {
        return selectList(getBaseQueryWrapper()
                .likeIfPresent(DictDataDo::getLabel, reqVo.getLabel())
                .eqIfPresent(DictDataDo::getDictType, reqVo.getDictType())
                .eqIfPresent(DictDataDo::getStatus, reqVo.getStatus()));
    }

    default List<DictDataDo> selectListByTypeAndStatus(String dictType, Integer status) {
        return selectList(new LambdaQueryWrapper<DictDataDo>().eq(DictDataDo::getDictType, dictType)
                .eq(DictDataDo::getStatus, status));
    }


}
