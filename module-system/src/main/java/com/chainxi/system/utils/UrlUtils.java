package com.chainxi.system.utils;

import jakarta.annotation.Nonnull;
import javafx.util.Pair;

import java.util.List;

/**
 * @Author : ChainXi
 * @Date : 2025/1/28 18:44
 * @Desc :
 */
public class UrlUtils {
    public static String joinParam(@Nonnull String url, @Nonnull List<Pair<String,Object>> params) {
        for (Pair<String, Object> param : params) {
            url = joinParam(url,param.getKey(),param.getValue());
        }
        return url;
    }
    /**
     * 在url上拼接上kv参数并返回
     *
     * @param url   url
     * @param key   参数名称
     * @param value 参数值
     * @return 拼接后的url字符串
     */
    public static String joinParam(@Nonnull String url, @Nonnull String key, Object value) {
        String paramStr = key + "=" + value;
        // 如果参数为空, 直接返回
        if (url == null) {
            url = "";
        }
        int index = url.lastIndexOf('?');
        // ? 不存在
        if (index == -1) {
            return url + '?' + paramStr;
        }
        // ? 是最后一位
        if (index == url.length() - 1) {
            return url + paramStr;
        }
        // ? 是其中一位
        if (index < url.length() - 1) {
            String separatorChar = "&";
            // 如果最后一位是 不是&, 且 paramStr 第一位不是 &, 就赠送一个 &
            if (url.lastIndexOf(separatorChar) != url.length() - 1 &&
                    paramStr.indexOf(separatorChar) != 0) {
                return url + separatorChar + paramStr;
            } else {
                return url + paramStr;
            }
        }
        // 正常情况下, 代码不可能执行到此
        return url;
    }


}
