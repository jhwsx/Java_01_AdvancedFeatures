package com.java.advanced.features.reflect.genericsclass;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * @author wangzhichao
 * @since 2020/6/7
 */
public class _05_WildcardTypeTest {
    public static void main(String[] args) {
        Class<PointWildcardImpl> clazz = PointWildcardImpl.class;
        // 获取此类直接实现的泛型接口对应的 Type 对象数组
        Type[] types = clazz.getGenericInterfaces();
        for (Type type : types) {
            System.out.println("type = " + type); // PointSingleInterface<java.lang.Comparable<? extends java.lang.Number>>
            ParameterizedType pointSingleInterfaceType = (ParameterizedType) type;
            // 获取替换类型参数的实际类型对应的 Type 对象的数组
            Type[] actualTypeArguments = pointSingleInterfaceType.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                if (actualTypeArgument instanceof ParameterizedType) {
                    ParameterizedType comparableType = (ParameterizedType) actualTypeArgument;
                    System.out.println("替换泛型接口的类型参数的实际类型为: " + comparableType); // java.lang.Comparable<? extends java.lang.Number>
                    // 对 java.lang.Comparable<? extends java.lang.Number> 获取替换类型参数的实际类型对应的 Type 对象的数组
                    Type[] comparableTypeActualTypeArguments = comparableType.getActualTypeArguments();
                    for (Type comparableTypeActualTypeArgument : comparableTypeActualTypeArguments) {
                        if (comparableTypeActualTypeArgument instanceof WildcardType) {
                            WildcardType wildcardType = (WildcardType) comparableTypeActualTypeArgument;
                            System.out.println("wildcardType = " + wildcardType);
                            Type[] upperBounds = wildcardType.getUpperBounds();
                            for (Type upperBound : upperBounds) {
                                Class bound = (Class) upperBound;
                                System.out.println("上边界 = " + bound);
                            }
                            Type[] lowerBounds = wildcardType.getLowerBounds();
                            for (Type lowerBound : lowerBounds) {
                                Class bound = (Class) lowerBound;
                                System.out.println("上边界 = " + bound);
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
type = com.java.advanced.features.reflect.genericsclass.PointSingleInterface<java.lang.Comparable<? extends java.lang.Number>>
替换泛型接口的类型参数的实际类型为: java.lang.Comparable<? extends java.lang.Number>
wildcardType = ? extends java.lang.Number
上边界 = class java.lang.Number
 */
