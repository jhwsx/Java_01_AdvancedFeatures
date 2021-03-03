package com.java.advanced.features.generics.tutorial.t03_generic_methods;


/**
 * 泛型方法
 *
 * 可以声明静态或者非静态的泛型方法，还可以声明泛型构造方法
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        // 显式的类型说明
        boolean same = Util.<Integer, String>compare(p1, p2);
        // 类型推断，不用显式的类型说明
        boolean same2 = Util.compare(p1, p2);
    }
}
