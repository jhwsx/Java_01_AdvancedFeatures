package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._05.after_type_erase;

public class Pair {

    public Pair(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    private Object key;
    private Object value;
}