package com.java.advanced.features.generics.tutorial.t07_wildcards._05_wildcard_capture_and_helper_methods;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/24
 */
public class WildcardErrorBad {
    static void swapFirst(List<? extends Number> l1, List<? extends Number> l2) {
        Number temp = l1.get(0);
        // l1.set(0, l2.get(0)); // 编译报错

        // l2.set(0, temp); // 编译报错
    }

    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(1, 2, 3);
        List<Double> ld = Arrays.asList(10.10, 20.20, 30.30);
        swapFirst(li, ld);
    }
}

/*
G:\IdeaProjects\Java_01_AdvancedFeatures\src>javac -Xdiags:verbose com/java/advanced/features/generics
/tutorial/t07_wildcards/_05_wildcard_capture_and_helper_me
thods/WildcardErrorBad.java
com\java\advanced\features\generics\tutorial\t07_wildcards\_05_wildcard_capture_and_helper_methods
\WildcardErrorBad.java:13: 错误: 无法将接口 List<E>中的方法 set应用到给定类型;
        l1.set(0, l2.get(0));
          ^
  需要: int,CAP#1
  找到: int,Number
  原因: 参数不匹配; Number无法转换为CAP#1
  其中, E是类型变量:
    E扩展已在接口 List中声明的Object
  其中, CAP#1,CAP#2是新类型变量:
    CAP#1从? extends Number的捕获扩展Number
    CAP#2从? extends Number的捕获扩展Number
com\java\advanced\features\generics\tutorial\t07_wildcards\_05_wildcard_capture_and_helper_methods\WildcardErrorBad.java:15: 错误: 无法将接口 List<E>中的方法 se
t应用到给定类型;
        l2.set(0, temp);
          ^
  需要: int,CAP#1
  找到: int,Number
  原因: 参数不匹配; Number无法转换为CAP#1
  其中, E是类型变量:
    E扩展已在接口 List中声明的Object
  其中, CAP#1是新类型变量:
    CAP#1从? extends Number的捕获扩展Number
2 个错误


 */
