package com.java.advanced.features.reflect.basicclass;

import java.lang.reflect.Array;

/**
 * @author wangzhichao
 * @since 2020/7/5
 */
public class _10_GetArrayComponentTypeTest {
    public static void main(String[] args) {
        String[] stringArray = (String[]) Array.newInstance(String.class, 1);
        Class<?> componentType = stringArray.getClass().getComponentType();
        System.out.println(componentType.getName());

        int[] intArray = {1, 2, 3};
        Class<?> componentType1 = intArray.getClass().getComponentType();
        System.out.println(componentType1.getName());

        String notArray = "";
        Class<?> componentType2 = notArray.getClass().getComponentType();
        System.out.println(componentType2);
    }
}

/*
打印：
java.lang.String
int
null
 */
