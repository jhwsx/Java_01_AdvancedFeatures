package com.java.advanced.features.generics.genericerase.wildcard;

import java.util.ArrayList;
import java.util.Collection;
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

        ArrayList<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple());
        Collection<? extends Fruit> fruits1 = appleList;
        ArrayList<Orange> oranges = new ArrayList<>();
        oranges.add(new Orange());
        Collection<? extends Fruit> fruits2 = oranges;
        ArrayList<Fruit> fruits3 = new ArrayList<>();
//        fruitList.addAll(fruits1);
//        fruitList.addAll(fruits2);
//        fruitList.addAll(fruits3);
        // 可以添加 null，但是没有意义
        fruitList.add(null);

        // ===改变===
        // 不允许改变任何类型的对象
        // fruitList.set(0, new Apple());
        // ===删除===
        fruitList.remove(new Apple());
        fruitList.remove(new Fruit());
        fruitList.removeAll(new ArrayList<Apple>());
        // 获取操作
        Fruit fruit = fruitList.get(0);

        List<Orange> orangeList = new ArrayList<>();
        addApple(orangeList);
        // Orange orange = orangeList.get(0);
    }

    private static void addApple(List<? extends Fruit> fruits) {
        // fruits.add(new Apple());
    }
}
