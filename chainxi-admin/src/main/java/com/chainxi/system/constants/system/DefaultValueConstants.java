package com.chainxi.system.constants.system;

import java.time.Duration;

public interface DefaultValueConstants {
    Integer CONFIG_AUTH_USER_AT_EXPIRE = (int) Duration
            .ofMinutes(30)
            .toSeconds();
    Integer CONFIG_AUTH_USER_RT_EXPIRE = (int) Duration
            .ofDays(7)
            .toSeconds();
    Integer CACHE_ENCRYPT_EXPIRE = 3600000;
    Integer CACHE_DICT_LABEL_LOCAL_EXPIRE = -1;
    Integer CACHE_DICT_LABEL_REMOTE_EXPIRE = 60000;
    Integer CACHE_DICT_VALUE_LOCAL_EXPIRE = -1;
    Integer CACHE_DICT_VALUE_REMOTE_EXPIRE = 60000;
    Integer CACHE_AUTH_USER_LOCAL_EXPIRE = -1;
    Integer CACHE_AUTH_USER_REMOTE_EXPIRE = 3600000;
    Integer CACHE_CAPTCHA_CODE_LOCAL_EXPIRE = 60000;
    Integer CACHE_CAPTCHA_CODE_REMOTE_EXPIRE = -1;
    Integer CACHE_DATA_PERMISSION_TABLE_LOCAL_EXPIRE = -1;
    Integer CACHE_DATA_PERMISSION_TABLE_REMOTE_EXPIRE = 600000;
}