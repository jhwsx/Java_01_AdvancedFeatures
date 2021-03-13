package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._05.before_type_erase;

public class Pair<K, V> {

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    private K key;
    private V value;
}