package com.java.advanced.features.generics.intro;

public class Test {
    public static void main(String[] args) {
        Point<Integer> integerPoint = new Point<>(1, 2);
        System.out.println("x = " + integerPoint.getX() + ", y = " + integerPoint.getY());
        Point<Float> floatPoint = new Point<>(1f, 2f);
        System.out.println("x = " + floatPoint.getX() + ", y = " + floatPoint.getY());
    }
}
/*
 打印結果：
 x = 1, y = 2
 x = 1.0, y = 2.0
 */
