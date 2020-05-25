package com.java.advanced.features.reflect.genericsclass;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _02_GetGenericInterfacesTest {
    public static void main(String[] args) {
        Class<PointImpl> clazz = PointImpl.class;
        // 获取此类直接实现的泛型接口对应的 Type 对象数组
        Type[] types = clazz.getGenericInterfaces(); // 这里就是 PointInterface<String, Double>
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                // 获取替换类型参数的实际类型对应的 Type 对象的数组
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    Class actualTypeArgumentClass = (Class) actualTypeArgument;
                    System.out.println("替换泛型接口的类型参数的实际类型为：" + actualTypeArgumentClass.getName());
                }
                // 获取声明泛型接口的原始接口对应的 Type 对象
                Type rawType = parameterizedType.getRawType();
                Class rawTypeClass = (Class) rawType;
                System.out.println("声明泛型接口的类型为：" + rawTypeClass.getName());
            }
        }
    }
}
/*
替换泛型接口的类型参数的实际类型为：java.lang.String
替换泛型接口的类型参数的实际类型为：java.lang.Double
声明泛型接口的类型为：com.java.advanced.features.reflect.genericsclass.PointInterface
 */