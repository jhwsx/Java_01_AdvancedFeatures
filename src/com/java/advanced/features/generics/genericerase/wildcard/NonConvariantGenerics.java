package com.java.advanced.features.generics.genericerase.wildcard;

import java.util.ArrayList;

public class NonConvariantGenerics {
    public static void main(String[] args) {
        // 编译错误：
//        Incompatible types.
//        Required:
//        ArrayList<com.java.advanced.features.generics.genericerase.wildcard.Fruit>
//        Found:
//        ArrayList<com.java.advanced.features.generics.genericerase.wildcard.Apple>

//        ArrayList<Fruit> fruitList = new ArrayList<Apple>();

        // 编译错误：
//        Incompatible types.
//        Required:
//        ArrayList<com.java.advanced.features.generics.genericerase.wildcard.Apple>
//        Found:
//        ArrayList<com.java.advanced.features.generics.genericerase.wildcard.Fruit>

//        ArrayList<Apple> appleList = new ArrayList<Fruit>();
    }
}
