package com.chainxi.common.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

public class DefaultDBFieldHandler implements MetaObjectHandler {
    //当mp实现添加操作的时候，这个方法执行
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime current = LocalDateTime.now();
        this.setFieldValByName("createTime", current, metaObject);
        this.setFieldValByName("updateTime", current, metaObject);
        this.setFieldValByName("updater", "anonymous", metaObject);

    }

    //当mp实现修改操作的时候，这个方法执行
    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime current = LocalDateTime.now();
        this.setFieldValByName("updateTime", current, metaObject);
        this.setFieldValByName("updater", "anonymous", metaObject);
    }
}