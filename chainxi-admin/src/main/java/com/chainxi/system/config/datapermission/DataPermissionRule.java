package com.chainxi.system.config.datapermission;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;

public interface DataPermissionRule {
    <T,R,Children extends AbstractWrapper<T, R, Children>> AbstractWrapper<T, R,Children> set(AbstractWrapper<T, R,Children> wrapper, R column);
    /**
     * 根据表名和别名，生成对应的 WHERE / OR 过滤条件
     *
     * @param tableName         表名
     * @param tableAlias        别名，可能为空
     * @param mappedStatementId
     * @return 过滤条件 Expression 表达式
     */
}
