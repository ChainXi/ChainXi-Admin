package com.chainxi.system.service.dict;


import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.bo.DictDataBo;
import com.chainxi.system.entity.dict.DictDataDo;
import com.chainxi.system.reqvo.dict.data.DictDataCreateReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataExportReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataPageReqVo;
import com.chainxi.system.reqvo.dict.data.DictDataUpdateReqVo;

import java.util.Collection;
import java.util.List;

/**
 * 字典数据 Service 接口
 *
 * @author chainxi
 */
public interface DictDataService {

    /**
     * 创建字典数据
     *
     * @param reqVo 字典数据信息
     * @return 字典数据编号
     */
    Long createDictData(DictDataCreateReqVo reqVo);

    /**
     * 更新字典数据
     *
     * @param reqVo 字典数据信息
     */
    void updateDictData(DictDataUpdateReqVo reqVo);

    /**
     * 删除字典数据
     *
     * @param id 字典数据编号
     */
    void deleteDictData(Long id);

    /**
     * 获得字典数据列表
     *
     * @return 字典数据全列表
     */
    List<DictDataDo> getDictDataList();

    /**
     * 获得字典数据分页列表
     *
     * @param reqVo 分页请求
     * @return 字典数据分页列表
     */
    PageResult<DictDataDo> getDictDataPage(DictDataPageReqVo reqVo);

    /**
     * 获得字典数据列表
     *
     * @param reqVo 列表请求
     * @return 字典数据列表
     */
    List<DictDataDo> getDictDataList(DictDataExportReqVo reqVo);

    /**
     * 获得字典数据列表
     *
     * @param dictType 字典类型
     * @return 字典数据列表
     */
    List<DictDataDo> getEnabledDictDataListByType(String dictType);

    /**
     * 获得字典数据详情
     *
     * @param id 字典数据编号
     * @return 字典数据
     */
    DictDataDo getDictData(Long id);

    /**
     * 校验字典数据们是否有效。如下情况，视为无效：
     * 1. 字典数据不存在
     * 2. 字典数据被禁用
     *
     * @param dictType 字典类型
     * @param values   字典数据值的数组
     */
    void validateDictDataList(String dictType, Collection<String> values);

    /**
     * 获得指定的字典数据
     *
     * @param dictType 字典类型
     * @param value    字典数据值
     * @return 字典数据
     */
    DictDataBo getDictData(String dictType, String value);

    /**
     * 解析获得指定的字典数据，从缓存中
     *
     * @param dictType 字典类型
     * @param label    字典数据标签
     * @return 字典数据
     */
    DictDataBo parseDictData(String dictType, String label);
}
