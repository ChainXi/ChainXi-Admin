package com.chainxi.system.service.dict;

import cn.hutool.core.util.StrUtil;
import com.chainxi.common.web.exception.BizException;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.utils.LocalDateTimeUtils;
import com.chainxi.system.constants.system.ErrorCodeConstants;
import com.chainxi.system.convert.dict.DictTypeConvert;
import com.chainxi.system.entity.dict.DictTypeDo;
import com.chainxi.system.mapper.dict.DictDataMapper;
import com.chainxi.system.mapper.dict.DictTypeMapper;
import com.chainxi.system.reqvo.dict.type.DictTypeCreateReqVo;
import com.chainxi.system.reqvo.dict.type.DictTypeExportReqVo;
import com.chainxi.system.reqvo.dict.type.DictTypeUpdateReqVo;
import com.chainxi.system.respvo.dict.type.DictTypePageReqVo;
import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
@RequiredArgsConstructor
public class DictTypeServiceImpl implements DictTypeService {
    private final DictTypeMapper dictTypeMapper;
    private final DictDataMapper dictDataMapper;

    @Override
    public long countByDictType(String dictType) {
        return dictDataMapper.selectCountByDictType(dictType);
    }

    @Override
    public PageResult<DictTypeDo> getDictTypePage(DictTypePageReqVo reqVo) {
        return dictTypeMapper.selectPage(reqVo);
    }

    @Override
    public List<DictTypeDo> getDictTypeList(DictTypeExportReqVo reqVo) {
        return dictTypeMapper.selectList(reqVo);
    }

    @Override
    public DictTypeDo getDictType(Long id) {
        return dictTypeMapper.selectById(id);
    }

    @Override
    public DictTypeDo getDictType(String type) {
        return dictTypeMapper.selectByType(type);
    }

    @Override
    public Long createDictType(DictTypeCreateReqVo reqVo) {
        // 校验正确性
        validateDictTypeForCreateOrUpdate(null, reqVo.getName(), reqVo.getType());

        // 插入字典类型
        DictTypeDo dictType = DictTypeConvert.INSTANCE
                .convert(reqVo)
                .setDeletedTime(LocalDateTimeUtils.EMPTY); // 唯一索引，避免 null 值
        dictTypeMapper.insert(dictType);
        return dictType.getId();
    }

    @Override
    public void updateDictType(DictTypeUpdateReqVo reqVo) {
        // 校验正确性
        validateDictTypeForCreateOrUpdate(reqVo.getId(), reqVo.getName(), null);

        // 更新字典类型
        DictTypeDo updateObj = DictTypeConvert.INSTANCE.convert(reqVo);
        dictTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteDictType(Long id) {
        // 校验是否存在
        DictTypeDo dictType = validateDictTypeExists(id);
        // 校验是否有字典数据
        if (dictDataMapper.selectCountByDictType(dictType.getType()) > 0) {
            throw new BizException(ErrorCodeConstants.DICT_TYPE_HAS_CHILDREN);
        }
        // 删除字典类型
        dictTypeMapper.updateToDelete(id, LocalDateTime.now());
    }

    @Override
    public List<DictTypeDo> getDictTypeList() {
        return dictTypeMapper.selectList();
    }

    private void validateDictTypeForCreateOrUpdate(Long id, String name, String type) {
        // 校验自己存在
        validateDictTypeExists(id);
        // 校验字典类型的名字的唯一性
        validateDictTypeNameUnique(id, name);
        // 校验字典类型的类型的唯一性
        validateDictTypeUnique(id, type);
    }

    @VisibleForTesting
    void validateDictTypeNameUnique(Long id, String name) {
        DictTypeDo dictType = dictTypeMapper.selectByName(name);
        if (dictType == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw new BizException(ErrorCodeConstants.DICT_TYPE_NAME_DUPLICATE);
        }
        if (!dictType.getId().equals(id)) {
            throw new BizException(ErrorCodeConstants.DICT_TYPE_NAME_DUPLICATE);
        }
    }

    @VisibleForTesting
    void validateDictTypeUnique(Long id, String type) {
        if (StrUtil.isEmpty(type)) {
            return;
        }
        DictTypeDo dictType = dictTypeMapper.selectByType(type);
        if (dictType == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw new BizException(ErrorCodeConstants.DICT_TYPE_TYPE_DUPLICATE);
        }
        if (!dictType.getId().equals(id)) {
            throw new BizException(ErrorCodeConstants.DICT_TYPE_TYPE_DUPLICATE);
        }
    }

    @VisibleForTesting
    DictTypeDo validateDictTypeExists(Long id) {
        if (id == null) {
            return null;
        }
        DictTypeDo dictType = dictTypeMapper.selectById(id);
        if (dictType == null) {
            throw new BizException(ErrorCodeConstants.DICT_TYPE_NOT_EXISTS);
        }
        return dictType;
    }

}
