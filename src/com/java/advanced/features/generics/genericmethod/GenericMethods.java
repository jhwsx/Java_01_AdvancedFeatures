package com.java.advanced.features.generics.genericmethod;

public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods genericMethods = new GenericMethods();
        genericMethods.f("Hello");
        genericMethods.f(1);
        genericMethods.f(true);
        // 显式的类型说明，那么括号里只能传入声明的类型
        genericMethods.<Double>f(1.0D);
    }
}
