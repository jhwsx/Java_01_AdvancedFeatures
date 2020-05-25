package com.java.advanced.features.reflect.genericsclass;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _03_TypeVariableTest {
    public static void main(String[] args) {
        Class<PointerGenericityImpl> clazz = PointerGenericityImpl.class;
        // 获取此类直接继承的接口对应的 Type 对象数组
        Type[] types = clazz.getGenericInterfaces(); // 即 PointInterface<T, Integer>
        for (Type type : types) {
            // 判断是参数化类型
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                // 获取替换泛型的类型参数的实际类型对应的 Type 对象数组
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments(); // T, Integer
                for (Type actualTypeArgument : actualTypeArguments) {
                    // 判断是类型参数, 未替换的
                    if (actualTypeArgument instanceof TypeVariable) {
                        TypeVariable typeVariable = (TypeVariable) actualTypeArgument;
                        System.out.println("类型参数的名字为：" + typeVariable.getName());
                        Type[] typeVariableBounds = typeVariable.getBounds(); // 上边界可以有多个，所以是个数组
                        for (Type typeVariableBound : typeVariableBounds) {
                            Class boundClass = (Class) typeVariableBound;
                            System.out.println("类型参数的上边界为：" + boundClass.getName());
                        }
                    }
                    // 判断是类，已经替换的
                    if (actualTypeArgument instanceof Class) {
                        Class acutalTypeArgumentClass = (Class) actualTypeArgument;
                        System.out.println("替换泛型的类型参数的实际类型为：" + acutalTypeArgumentClass.getName());
                    }
                }
            }
        }
    }
}

/*
类型参数的名字为：T
类型参数的上边界为：java.lang.Number
类型参数的上边界为：java.io.Serializable
替换泛型的类型参数的实际类型为：java.lang.Integer
 */
