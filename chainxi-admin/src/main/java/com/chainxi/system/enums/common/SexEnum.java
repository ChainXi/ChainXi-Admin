package com.chainxi.system.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum SexEnum {

    /**
     * 男
     */
    MALE(1),
    /**
     * 女
     */
    FEMALE(2),
    /* 未知 */
    UNKNOWN(3);

    /**
     * 性别
     */
    private final Integer sex;

}
