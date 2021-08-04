package com.java.advanced.features.io.bixiangdong.p16_recursion;

/**
 * 递归：
 * 1，明确退出条件，避免栈溢出；
 * 2，注意递归次数太多也会栈溢出
 * @author wangzhichao
 * @since 2021/7/28
 */
public class RecursionDemo {
    public static void main(String[] args) {
        System.out.println(getSum(5));
    }

    public static int getSum(int num) {
        if (num == 1)
            return num;
        return num + getSum(num - 1);
    }
}
