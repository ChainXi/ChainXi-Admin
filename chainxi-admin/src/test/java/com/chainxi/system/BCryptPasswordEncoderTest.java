package com.chainxi.system;

import com.baomidou.mybatisplus.core.conditions.segments.ColumnSegment;
import com.chainxi.common.web.utils.ValidationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.cache.support.NullValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Base64;

public class BCryptPasswordEncoderTest {
    @Test
    void getPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("admin");
        System.out.println(password);
    }

    @Test
    void getList() {
        System.out.println(Arrays.asList(new Integer[]{100, 200}));
    }

    /**
     * 获取 columnName
     */
    protected final <R> ColumnSegment columnToSqlSegment(R column) {
        return () -> columnToString(column);
    }

    /**
     * 获取 columnName
     */
    protected  <R> String columnToString(R column) {
        return (String) column;
    }

    @Test
    void testNullAble() {
        Object instance = NullValue.INSTANCE;
        assert null != instance && !instance.equals((Object) null);
        assert instance != null;
    }

    @Test
    void testCustomerParams() {
        Integer[] integers = {12, 2, 3, 5};
        testCustomerParamsSub(integers);
    }

    @Test
    void testPhoneNumber() {
        assert ValidationUtils.isMobile("17582369652");
    }

    @Test
    void testCustomerParamsSub(Integer... params) {
    }

    @Test
    void testCustomer() {
        System.out.println(byte[].class);
    }

    @Test
    void getName() throws InvalidKeyException {
        String data =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAihy0BicS" +
                        "/UKtenXul2ItGH8nXecoZCybe9XDzEVYOxDs3P28sLIfcD1XAAJMvgPfs3CF1XDeb2WjLAVjBDhiAHR8Mo9yz1wmRargBJvDTxTiKFzukVz6xXLoOOgieL2+YKQilI8S+8Agqb3omVdGJX9SVlL4aXEoLZZ0qAVnl77LPhegqVnzlu1E8kNJ47nYI3SQNZm1LoUbs7wM5GjR83z5quD++eeCxjRvR+mg2UP8/kwAxP4sqmjFbd+brxxXK4P5b2G/I3jCTUCgznNI8PHvm752DUg7T3ijbRBR0CNQz/0SMKd3mXD+ZP8XJ7zOKaGXHb4eui8/1JbkcfaEPQIDAQAB";
        byte[] bytes = Base64.getDecoder().decode(data);
/*        bytes = RSAPublicKeyImpl.newKey(bytes).getEncoded();
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));
        System.out.println(DigestUtil.md5Hex(data));*/
    }


}
