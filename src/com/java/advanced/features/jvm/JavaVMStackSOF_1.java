package com.java.advanced.features.jvm;

/**
 * VM args： -Xss128k
 * 使用-Xss参数减少栈内存容量
 * 结果：抛出StackOverflowError异常，异常出现时输出的堆栈深度相应缩小。
 * @author wangzhichao
 * @since 2020/3/29
 */
public class JavaVMStackSOF_1 {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF_1 javaVMStackSOF = new JavaVMStackSOF_1();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + javaVMStackSOF.stackLength);
            throw e;
        }
    }

}
