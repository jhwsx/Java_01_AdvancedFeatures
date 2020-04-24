package com.java.advanced.features.generics.tutorial.t02_generic_types._05_multiple_type_parameters;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public interface Pair<K, V> {
    public K getKey();

    public V getValue();
}
