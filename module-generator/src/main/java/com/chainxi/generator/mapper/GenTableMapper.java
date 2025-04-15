package com.chainxi.generator.mapper;

import com.chainxi.common.web.core.mapper.BaseMapperX;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.generator.domain.GenTable;
import com.chainxi.generator.reqvo.GenTablePageQueryReqVo;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author chainxi
 */
public interface GenTableMapper extends BaseMapperX<GenTable> {
    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    default PageResult<GenTable> selectGenTableList(GenTablePageQueryReqVo genTable) {
        return selectPage(genTable, getBaseQueryWrapper()
                .select(GenTable::getTableId, GenTable::getTableName, GenTable::getTableComment,
                        GenTable::getGenType, GenTable::getClassName, GenTable::getCreateTime,
                        GenTable::getUpdateTime)
                .likeIfPresent(GenTable::getTableName, genTable.getTableName())
                .likeIfPresent(GenTable::getTableComment, genTable.getTableComment()));
    }

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    public List<GenTable> selectGenTableAll();

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    public GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    public GenTable selectGenTableByName(String tableName);

    /**
     * 新增业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    public int insertGenTable(GenTable genTable);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    public int updateGenTable(GenTable genTable);

    /**
     * 批量删除业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGenTableByIds(Long[] ids);

    /**
     * 创建表
     *
     * @param sql 表结构
     * @return 结果
     */
    public int createTable(String sql);
}
