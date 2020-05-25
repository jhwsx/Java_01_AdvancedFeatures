package com.java.advanced.features.reflect.genericsclass;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _04_GenericArrayTypeTest {
    public static void main(String[] args) {
        Class<IntegerArrayHolder> clazz = IntegerArrayHolder.class;
        // 获取此类直接实现的接口对应的 Type 对象数组
        Type[] types = clazz.getGenericInterfaces(); // Holder<Integer[]>
        for (Type type : types) {
            // 是参数化类型
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                // 获取替换了泛型的类型参数的实际类型对应的 Type 数组
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof GenericArrayType) {
                        GenericArrayType genericArrayType = (GenericArrayType) actualTypeArgument;
                        System.out.println("类型参数为：" + genericArrayType.getTypeName());
                        Type genericComponentType = genericArrayType.getGenericComponentType();
                        System.out.println("类型参数的组件类型为：" + genericComponentType.getTypeName());
                    }
                }
            }
        }

    }
}

/*
类型参数为：T[]
类型参数的组件类型为：T
 */
