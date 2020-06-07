package com.java.advanced.features.reflect.genericsclass.blog;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wangzhichao
 * @since 2020/6/7
 */
public class _04_GenericArrayTypeTest {
    public static void main(String[] args) {
        Type[] types = ListArrayGenerator.class.getGenericInterfaces();
        for (Type type : types) {
            System.out.println("type = " + type); // Generator<List<Integer>[]>
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                // 获取替换了泛型类型参数的实际类型的Type数组
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof GenericArrayType) {
                        GenericArrayType genericArrayType = (GenericArrayType) actualTypeArgument;
                        System.out.println("替换了泛型类型参数的实际类型为： " + genericArrayType);
                        Type genericComponentType = genericArrayType.getGenericComponentType();
                        System.out.println("泛型数组类型的元素类型为： " + genericComponentType);
                        if (genericComponentType instanceof ParameterizedType) {
                            System.out.println("泛型数组类型的元素类型是ParameterizedType");
                        }
                    }
                }
            }
        }
    }
}
