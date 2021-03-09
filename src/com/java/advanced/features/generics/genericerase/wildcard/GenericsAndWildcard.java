package com.java.advanced.features.generics.genericerase.wildcard;

import java.util.ArrayList;

/**
 * @author wangzhichao
 * @since 2021/3/9
 */
public class GenericsAndWildcard {
    public static void main(String[] args) {
        ArrayList<?> arrayList = new ArrayList<>();
        ArrayList<Apple> appleArrayList = new ArrayList<>();
        ArrayList<Fruit> fruitArrayList = new ArrayList<>();
        ArrayList<Object> objectArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        arrayList = appleArrayList;
        arrayList = fruitArrayList;
        arrayList = objectArrayList;
        arrayList = stringArrayList;

        // ====不可以添加===
//        arrayList.add(new Apple()); // 错误
//        arrayList.add(new Object()); // 错误
//        arrayList.add(""); // 错误
        // ====可以删除====
        arrayList.remove(""); // OK
        // ====获取元素====
        Object o = arrayList.get(0); // OK, 返回类型是 Object 类型。
    }
}
