package com.chainxi.common.web.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public abstract class BaseDo extends LogicLessBaseDo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 是否删除
     */
    @TableLogic
    protected Boolean deleted;

}
