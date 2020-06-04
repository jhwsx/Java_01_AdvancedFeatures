package com.java.advanced.features.reflect.basicclass;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _04_GetInterfacesTest {
    public static void main(String[] args) {
        // getInterfaces()将获取指定类直接继承的接口列表
        // 对于实现有多个接口的类
        printGetInterfaces(SuperHero.class);
        // 对于继承有接口的接口
        printGetInterfaces(SuperHearSmell.class);
        // 对于不实现任何接口的接口
        printGetInterfaces(SuperPower.class);
        // 对于实现一个接口的类
        printGetInterfaces(Man.class);
        // 对于基本类型或 void
        printGetInterfaces(int.class);
        printGetInterfaces(void.class);
        // 对于数组类型
        printGetInterfaces(String[].class);

        Class<?>[] interfaces = SuperHero.class.getInterfaces();
        System.out.println("interfaces[0] = " + interfaces[0]);
        System.out.println("interfaces[1] = " + interfaces[1]);
        System.out.println("interfaces[2] = " + interfaces[2]);
    }

    private static <T> void printGetInterfaces(Class<T> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length == 0) {
            System.out.println("返回数组长度为 0。");
            System.out.println();
            return;
        }
        for (Class<?> element : interfaces) {
            System.out.println(element.getName());
        }
        System.out.println();
    }
}
/*
com.java.advanced.features.reflect.basicclass.XRayVision
com.java.advanced.features.reflect.basicclass.SuperHearing
com.java.advanced.features.reflect.basicclass.SuperSmell

com.java.advanced.features.reflect.basicclass.SuperHearing
com.java.advanced.features.reflect.basicclass.SuperSmell

返回数组长度为 0。

com.java.advanced.features.reflect.basicclass.Workable

返回数组长度为 0。

返回数组长度为 0。

java.lang.Cloneable
java.io.Serializable

interfaces[0] = com.java.advanced.features.reflect.basicclass.XRayVision
interfaces[1] = com.java.advanced.features.reflect.basicclass.SuperHearing
interfaces[2] = com.java.advanced.features.reflect.basicclass.SuperSmell
 */
