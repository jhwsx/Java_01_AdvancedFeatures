package com.java.advanced.features.generics.genericerase.cost;

class Generic<T> {}

public class ArrayOfGenericReference {
    static Generic<Integer>[] gia; // 这样是 ok 的
    // static Generic<Integer>[] array = new Generic<Integer>[10]; // 编译报错：Generic array creation
}
