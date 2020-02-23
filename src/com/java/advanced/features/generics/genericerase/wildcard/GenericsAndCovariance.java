package com.java.advanced.features.generics.genericerase.wildcard;

import java.util.ArrayList;
import java.util.List;

public class GenericsAndCovariance {
    public static void main(String[] args) {
        ArrayList<? extends Fruit> fruitList = new ArrayList<Apple>();
        // 修改操作
        // ===增加===
        // 不允许添加任何类型的对象
//        fruitList.add(new Apple());
//        fruitList.add(new Fruit());
//        fruitList.add(new Orange());
//        fruitList.add(new Object());
        // 可以添加 null，但是没有意义
        fruitList.add(null);
        // ===改变===
        // 不允许改变任何类型的对象
        // fruitList.set(0, new Apple());
        // ===删除===
        fruitList.remove(new Apple());
        fruitList.remove(new Fruit());
        // 获取操作
        Fruit fruit = fruitList.get(0);

    }
}
