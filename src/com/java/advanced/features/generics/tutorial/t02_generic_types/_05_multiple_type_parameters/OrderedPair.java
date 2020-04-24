package com.java.advanced.features.generics.tutorial.t02_generic_types._05_multiple_type_parameters;

import com.java.advanced.features.generics.tutorial.t02_generic_types._02_a_generic_version_of_the_box_class.Box;

/**
 * 多种类型的参数演示
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class OrderedPair<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public static void main(String[] args) {
        // 把 String, Integer 传递给了 K, V
        Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8); // 8 是 int 型，自动装箱为 Integer 型。
        Pair<String, String> p2 = new OrderedPair<String, String>("hello", "world");
        // 使用菱形表示法
        OrderedPair<String, Integer> p3 = new OrderedPair<>("Even", 8);
        OrderedPair<String, String> p4 = new OrderedPair<>("hello", "world");
        // 使用参数化类型，如List<String> 来替代类型参数
        OrderedPair<String, Box<Integer>> p = new OrderedPair<>("primes", new Box<>());
    }
}
