package com.java.advanced.features.reflect.genericsclass.type.useingson.my;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MyTypeToken<T> {
    final Type type;

    protected MyTypeToken() {
        // 获取泛型超类
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter");
        } else {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            type = actualTypeArguments[0];
        }
    }

    public Type getType() {
        return type;
    }
}
