package com.java.advanced.features.io.serialize.serializable;

import java.io.Serializable;

public class SingletonReflectFix implements Serializable {

    private static final long serialVersionUID = 1L;

    private SingletonReflectFix() {
        //no instance
        //Prevent form the reflection api.
        if (!getInstanceInvoked){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        System.out.println("SingletonFix constructor");
        getInstanceInvoked = false;
    }

    private static boolean getInstanceInvoked = false;
    public static SingletonReflectFix getInstance() {
        getInstanceInvoked = true;
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static SingletonReflectFix instance = new SingletonReflectFix();
    }

    private Object readResolve() {
        return SingletonHolder.instance;
    }
}