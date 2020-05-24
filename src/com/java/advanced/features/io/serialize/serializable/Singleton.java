package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

public class Singleton implements Serializable {
    private static final long serialVersionUID = 1L;

    private Singleton() {
        //no instance
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static Singleton instance = new Singleton();
    }
}