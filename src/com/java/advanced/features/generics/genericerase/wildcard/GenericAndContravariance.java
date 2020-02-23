package com.java.advanced.features.generics.genericerase.wildcard;

import java.util.ArrayList;

public class GenericAndContravariance {
    public static void main(String[] args) {
        ArrayList<? super Apple> apples = new ArrayList<Fruit>();
        // 存
        apples.add(new Apple());
        apples.add(new Hongfushi());
        // 编译错误
        // apples.add(new Fruit());
        // 取
        Object object = apples.get(0);
    }
}
