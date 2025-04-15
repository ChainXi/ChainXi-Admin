package com.chainxi.system.service.dict;


import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.entity.dict.DictTypeDo;
import com.chainxi.system.reqvo.dict.type.DictTypeCreateReqVo;
import com.chainxi.system.reqvo.dict.type.DictTypeExportReqVo;
import com.chainxi.system.reqvo.dict.type.DictTypeUpdateReqVo;
import com.chainxi.system.respvo.dict.type.DictTypePageReqVo;

import java.util.List;


public interface DictTypeService {

    /**
     * 获得指定字典类型的数据数量
     *
     * @param dictType 字典类型
     * @return 数据数量
     */
    long countByDictType(String dictType);

    /**
     * 创建字典类型
     *
     * @param reqVo 字典类型信息
     * @return 字典类型编号
     */
    Long createDictType(DictTypeCreateReqVo reqVo);

    /**
     * 更新字典类型
     *
     * @param reqVo 字典类型信息
     */
    void updateDictType(DictTypeUpdateReqVo reqVo);

    /**
     * 删除字典类型
     *
     * @param id 字典类型编号
     */
    void deleteDictType(Long id);

    /**
     * 获得字典类型分页列表
     *
     * @param reqVo 分页请求
     * @return 字典类型分页列表
     */
    PageResult<DictTypeDo> getDictTypePage(DictTypePageReqVo reqVo);

    /**
     * 获得字典类型列表
     *
     * @param reqVo 列表请求
     * @return 字典类型列表
     */
    List<DictTypeDo> getDictTypeList(DictTypeExportReqVo reqVo);

    /**
     * 获得字典类型详情
     *
     * @param id 字典类型编号
     * @return 字典类型
     */
    DictTypeDo getDictType(Long id);

    /**
     * 获得字典类型详情
     *
     * @param type 字典类型
     * @return 字典类型详情
     */
    DictTypeDo getDictType(String type);

    /**
     * 获得全部字典类型列表
     *
     * @return 字典类型列表
     */
    List<DictTypeDo> getDictTypeList();


}
