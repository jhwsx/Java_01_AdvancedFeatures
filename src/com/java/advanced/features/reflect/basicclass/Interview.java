package com.java.advanced.features.reflect.basicclass;

/**
 * https://www.cnblogs.com/javaee6/p/3714716.html?utm_source=tuicool&utm_medium=referral
 * 考察类的初始化的面试题目
 */
class SingleTon {
    private static SingleTon singleTon = new SingleTon();
    public static int count1;
    public static int count2 = 0;
 
    private SingleTon() {
        count1++;
        count2++;
    }
 
    public static SingleTon getInstance() {
        return singleTon;
    }
}
 
public class Interview {
    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.getInstance();
        System.out.println("count1=" + singleTon.count1);
        System.out.println("count2=" + singleTon.count2);
    }
}