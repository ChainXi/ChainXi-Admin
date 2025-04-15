/*
 *    Copyright 2009-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.chainxi.common.web.handler;

import cn.hutool.core.text.StrPool;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import jakarta.annotation.Nullable;
import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Clinton Begin
 */
public class EnumArrayOrdinalTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E[]> {

    private final Class<E> type;
    private final E[] enums;

    @SuppressWarnings("unchecked")
    public EnumArrayOrdinalTypeHandler(Class<E[]> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        Class<E> componentType = (Class<E>) type.getComponentType();
        this.type  = componentType;
        this.enums = componentType.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(
                    type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps,
            int i,
            E[] parameter,
            JdbcType jdbcType) throws SQLException {
        ps.setString(i,
                Arrays
                        .stream(parameter)
                        .map((e) -> String.valueOf(e.ordinal()))
                        .collect(Collectors.joining(StrPool.COMMA)));
    }

    @Override
    public E[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return extractArray(rs.getString(columnName));
    }

    @Override
    public E[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return extractArray(rs.getString(columnIndex));
    }

    @Override
    public E[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return extractArray(cs.getString(columnIndex));
    }

    private E toOrdinalEnum(int ordinal) {
        try {
            return enums[ordinal];
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Cannot convert " + ordinal + " to " + type.getSimpleName() +
                            " by ordinal value.", ex);
        }
    }

    @Nullable
    private E[] extractArray(String raw) {
        if (!StringUtils.hasText(raw)) {
            return null;
        }


        return Arrays
                .stream(raw.split(StrPool.COMMA))
                .map(e -> toOrdinalEnum(Integer.parseInt(e)))
                .toList()
                .toArray((E[]) Array.newInstance(type, 0));
    }

}
