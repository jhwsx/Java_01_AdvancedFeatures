package com.java.advanced.features.reflect.basicclass;

/**
 * https://www.cnblogs.com/xiohao/p/8853462.html
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _07_ClassForNameTest {
    public static void main(String[] args) throws ClassNotFoundException {

        String fullName = "com.java.advanced.features.reflect.basicclass.Foo";

        Class.forName(fullName);
        System.out.println("------end------");

        ClassLoader classLoader = _07_ClassForNameTest.class.getClassLoader();
        Class.forName(fullName, false, classLoader);
        System.out.println("------end------");
    }
}
