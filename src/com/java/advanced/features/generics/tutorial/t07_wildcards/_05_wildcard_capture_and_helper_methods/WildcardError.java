package com.java.advanced.features.generics.tutorial.t07_wildcards._05_wildcard_capture_and_helper_methods;

import java.util.List;

/**
 * wildcard capture
 *
 * @author wangzhichao
 * @since 2020/4/24
 */
public class WildcardError {
    void foo(List<?> i) {
        Object o = i.get(0);
        // i.set(0, o); // 此行编译错误，暂时注释掉
    }

    public static void main(String[] args) {

    }
}

/*
D:\Workspace\Java_01_AdvancedFeatures\src>javac -Xdiags:verbose -encoding utf-8 com/java/advanced/features/generics/tutorial/t07_wildcards/_05_wildcard_capture_and_helper_methods/Wildca
rdError.java
com\java\advanced\features\generics\tutorial\t07_wildcards\_05_wildcard_capture_and_helper_methods\WildcardError.java:14: 错误: 无法将接口 List<E>中的方法 set应用到给定类型;
        i.set(0, o); // 此行编译错误，暂时注释掉
         ^
  需要: int,CAP#1
  找到: int,Object
  原因: 参数不匹配; Object无法转换为CAP#1
  其中, E是类型变量:
    E扩展已在接口 List中声明的Object
  其中, CAP#1是新类型变量:
    CAP#1从?的捕获扩展Object
1 个错误
 */
