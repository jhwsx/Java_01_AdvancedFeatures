package com.java.advanced.features.generics.tutorial.t09_restrictions_on_generics._01_cannot_instantiate_generic_types_with_primitive_types;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static void main(String[] args) {
        // 不可以使用基本类型实例化泛型
        // Pair<int, char> pair = new Pair<>(8, 'a');
        Pair<Integer, Character> pair2 = new Pair<>(8, 'a');
        // 等价于下面的代码，java 编译器会进行自动装箱：
        Pair<Integer, Character> pair3 = new Pair<>(Integer.valueOf(8), Character.valueOf('a'));
    }
}
