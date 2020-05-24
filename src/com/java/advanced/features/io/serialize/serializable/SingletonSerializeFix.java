package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

public class SingletonSerializeFix implements Serializable {
    private static final long serialVersionUID = 1L;

    private SingletonSerializeFix() {
        //no instance
    }

    public static SingletonSerializeFix getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static SingletonSerializeFix instance = new SingletonSerializeFix();
    }

    private Object readResolve() {
        return SingletonHolder.instance;
    }
}