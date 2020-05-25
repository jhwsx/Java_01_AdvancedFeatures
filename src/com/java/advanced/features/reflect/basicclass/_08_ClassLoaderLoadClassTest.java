package com.java.advanced.features.reflect.basicclass;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _08_ClassLoaderLoadClassTest {
    public static void main(String[] args) throws ClassNotFoundException {

        String fullName = "com.java.advanced.features.reflect.basicclass.Foo";

        ClassLoader classLoader = _08_ClassLoaderLoadClassTest.class.getClassLoader();
        classLoader.loadClass(fullName);

        System.out.println("------end------");
    }
}
