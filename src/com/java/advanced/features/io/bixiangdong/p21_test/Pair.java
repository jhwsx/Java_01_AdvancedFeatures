package com.java.advanced.features.io.bixiangdong.p21_test;

/**
 * @author wangzhichao
 * @since 2021/8/4
 */
public class Pair<F, S> {
    public F first;
    public S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
