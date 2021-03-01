package com.java.advanced.features.reflect.genericsclass.type.useingson.you;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wangzhichao
 * @since 2021/3/2
 */
public class YourTypeToken<T> {
    private final Type type;

    protected YourTypeToken() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new IllegalArgumentException("not a parameterized type");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superClass;
        type = parameterizedType.getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}
