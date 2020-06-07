package com.java.advanced.features.reflect.genericsclass.blog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * @author wangzhichao
 * @since 2020/6/7
 */
public class _05_WildcardTypeTest {
    public static void main(String[] args) {
        Type[] types = NumberListGenerator.class.getGenericInterfaces();
        for (Type type : types) {
            System.out.println("type = " + type); // Generator<List<? extends Number>>
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof ParameterizedType) {
                        ParameterizedType typeArgument = (ParameterizedType) actualTypeArgument;
                        Type[] actualTypeArguments1 = typeArgument.getActualTypeArguments();
                        for (Type type1 : actualTypeArguments1) {
                            if (type1 instanceof WildcardType) {
                                WildcardType wildcardType = (WildcardType) type1;
                                System.out.println("wildcardType = " + wildcardType);
                                Type[] lowerBounds = wildcardType.getLowerBounds();
                                for (Type lowerBound : lowerBounds) {
                                    System.out.println("lowerBound = " + lowerBound);
                                }
                                Type[] upperBounds = wildcardType.getUpperBounds();
                                for (Type upperBound : upperBounds) {
                                    System.out.println("upperBound = " + upperBound);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
