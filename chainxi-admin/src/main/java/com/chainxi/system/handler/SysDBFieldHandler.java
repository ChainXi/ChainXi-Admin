package com.chainxi.system.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chainxi.system.bo.AuthUserBo;
import com.chainxi.system.utils.SecurityFrameworkUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

public class SysDBFieldHandler implements MetaObjectHandler {
    //当mp实现添加操作的时候，这个方法执行
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime current = LocalDateTime.now();
        this.setFieldValByName("createTime", current, metaObject);
        this.setFieldValByName("updateTime", current, metaObject);
        AuthUserBo authUserBO = SecurityFrameworkUtils.getLoginUser();
        if (authUserBO != null) {
            this.setFieldValByName("updater", "sys:" + authUserBO.getUserId(), metaObject);
        }
    }

    //当mp实现修改操作的时候，这个方法执行
    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime current = LocalDateTime.now();
        this.setFieldValByName("updateTime", current, metaObject);
        AuthUserBo authUserBO = SecurityFrameworkUtils.getLoginUser();
        if (authUserBO != null) {
            this.setFieldValByName("updater", "sys:" + authUserBO.getUserId(), metaObject);
        }
    }
}