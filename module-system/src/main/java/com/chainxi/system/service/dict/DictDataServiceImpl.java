package com.chainxi.system.service.dict;

import cn.hutool.core.collection.CollUtil;
import com.chainxi.common.web.constant.CommonStatusEnum;
import com.chainxi.common.web.exception.BizException;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.bo.DictDataBo;
import com.chainxi.system.constants.system.CacheKeyConstants;
import com.chainxi.system.constants.system.ErrorCodeConstants;
import com.chainxi.system.convert.dict.DictDataConvert;
import com.chainxi.system.entity.dict.DictDataDo;
import com.chainxi.system.entity.dict.DictTypeDo;
import com.chainxi.system.mapper.dict.DictDataMapper;
import com.chainxi.system.reqvo.dict.data.DictDataCreateReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataExportReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataPageReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataUpdateReqVo;
import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 字典数据 Service 实现类
 *
 * @author chainxi
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DictDataServiceImpl implements DictDataService {

    /**
     * 排序 dictType > sort
     */
    private static final Comparator<DictDataDo> COMPARATOR_TYPE_AND_SORT =
            Comparator.comparing(DictDataDo::getDictType).thenComparingInt(DictDataDo::getSort);

    private final DictTypeService dictTypeService;

    private final DictDataMapper dictDataMapper;

    @Override
    public List<DictDataDo> getDictDataList() {
        List<DictDataDo> list = dictDataMapper.selectEnableList();
        list.sort(COMPARATOR_TYPE_AND_SORT);
        return list;
    }

    @Override
    public PageResult<DictDataDo> getDictDataPage(DictDataPageReqVo reqVo) {
        return dictDataMapper.selectPage(reqVo);
    }

    @Override
    public List<DictDataDo> getDictDataList(DictDataExportReqVo reqVo) {
        List<DictDataDo> list = dictDataMapper.selectList(reqVo);
        list.sort(COMPARATOR_TYPE_AND_SORT);
        return list;
    }

    @Override
    public List<DictDataDo> getEnabledDictDataListByType(String dictType) {
        List<DictDataDo> list = dictDataMapper.selectListByTypeAndStatus(dictType,
                CommonStatusEnum.ENABLE.getStatus());
        list.sort(COMPARATOR_TYPE_AND_SORT);
        return list;
    }

    @Override
    public DictDataDo getDictData(Long id) {
        return dictDataMapper.selectById(id);
    }

    @Override
    public Long createDictData(DictDataCreateReqVo reqVo) {
        // 校验正确性
        validateDictDataForCreateOrUpdate(null, reqVo.getValue(), reqVo.getDictType());

        // 插入字典类型
        DictDataDo dictData = DictDataConvert.INSTANCE.convert(reqVo);
        dictDataMapper.insert(dictData);
        return dictData.getId();
    }

    @Override
    public void updateDictData(DictDataUpdateReqVo reqVo) {
        // 校验正确性
        validateDictDataForCreateOrUpdate(reqVo.getId(), reqVo.getValue(), reqVo.getDictType());

        // 更新字典类型
        DictDataDo updateObj = DictDataConvert.INSTANCE.convert(reqVo);
        dictDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteDictData(Long id) {
        // 校验是否存在
        validateDictDataExists(id);

        // 删除字典数据
        dictDataMapper.deleteById(id);
    }

    private void validateDictDataForCreateOrUpdate(Long id, String value, String dictType) {
        // 校验自己存在
        validateDictDataExists(id);
        // 校验字典类型有效
        validateDictTypeExists(dictType);
        // 校验字典数据的值的唯一性
        validateDictDataValueUnique(id, dictType, value);
    }

    @VisibleForTesting
    public void validateDictDataValueUnique(Long id, String dictType, String value) {
        DictDataDo dictData = dictDataMapper.selectByDictTypeAndValue(dictType, value);
        if (dictData == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典数据
        if (id == null) {
            throw new BizException(ErrorCodeConstants.DICT_DATA_VALUE_DUPLICATE);
        }
        if (!dictData.getId().equals(id)) {
            throw new BizException(ErrorCodeConstants.DICT_DATA_VALUE_DUPLICATE);
        }
    }

    @VisibleForTesting
    public void validateDictDataExists(Long id) {
        if (id == null) {
            return;
        }
        DictDataDo dictData = dictDataMapper.selectById(id);
        if (dictData == null) {
            throw new BizException(ErrorCodeConstants.DICT_DATA_NOT_EXISTS);
        }
    }

    @VisibleForTesting
    public void validateDictTypeExists(String type) {
        DictTypeDo dictType = dictTypeService.getDictType(type);
        if (dictType == null) {
            throw new BizException(ErrorCodeConstants.DICT_TYPE_NOT_EXISTS);
        }
        if (!CommonStatusEnum.ENABLE.getStatus().equals(dictType.getStatus())) {
            throw new BizException(ErrorCodeConstants.DICT_TYPE_NOT_ENABLE);
        }
    }

    @Override
    public void validateDictDataList(String dictType, Collection<String> values) {
        if (CollUtil.isEmpty(values)) {
            return;
        }
        Map<String, DictDataDo> dictDataMap = dictDataMapper
                .selectByDictTypeAndValues(dictType, values)
                .stream()
                .collect(Collectors.toMap(DictDataDo::getValue, Function.identity()));

        // 校验
        values.forEach(value -> {
            DictDataDo dictData = dictDataMap.get(value);
            if (dictData == null) {
                throw new BizException(ErrorCodeConstants.DICT_DATA_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(dictData.getStatus())) {
                throw new BizException(ErrorCodeConstants.DICT_DATA_NOT_ENABLE,
                        dictData.getLabel());
            }
        });
    }

    @Override
    @Cacheable(value = CacheKeyConstants.DICT_VALUE, key = "#dictType +'#'+ #value")
    public DictDataBo getDictData(String dictType, String value) {
        return DictDataConvert.INSTANCE.convert02(dictDataMapper.selectByDictTypeAndValue(dictType,
                value));
    }

    @Override
    @Cacheable(value = CacheKeyConstants.DICT_VALUE, key = "#dictType +'#'+ #label")
    public DictDataBo parseDictData(String dictType, String label) {
        return DictDataConvert.INSTANCE.convert02(dictDataMapper.selectByDictTypeAndLabel(dictType,
                label));
    }

}
