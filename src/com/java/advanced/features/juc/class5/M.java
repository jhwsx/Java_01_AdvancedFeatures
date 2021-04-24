package com.java.advanced.features.juc.class5;

/**
 * @author wangzhichao
 * @since 2021/4/24
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
