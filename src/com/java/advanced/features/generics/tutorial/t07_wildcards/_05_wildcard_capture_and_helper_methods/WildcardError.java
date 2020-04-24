package com.java.advanced.features.generics.tutorial.t07_wildcards._05_wildcard_capture_and_helper_methods;

import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/24
 */
public class WildcardError {
    void foo(List<?> i) {
        // i.set(0, i.get(0)); // 此行编译错误，暂时注释掉
    }

    public static void main(String[] args) {

    }
}

/*
G:\IdeaProjects\Java_01_AdvancedFeatures\src>javac -Xdiags:verbose
com/java/advanced/features/generics/tutorial/t07_wildcards/_05_wildcard_capture_and_helper_me
thods/WildcardError.java
com\java\advanced\features\generics\tutorial\t07_wildcards\_05_wildcard_capture_and_
helper_methods\WildcardError.java:11: 错误: 无法将接口 List<E>中的方法 set应
用到给定类型;
        i.set(0, i.get(0));
         ^
  需要: int,CAP#1
  找到: int,CAP#2
  原因: 参数不匹配; Object无法转换为CAP#1
  其中, E是类型变量:
    E扩展已在接口 List中声明的Object
  其中, CAP#1,CAP#2是新类型变量:
    CAP#1从?的捕获扩展Object
    CAP#2从?的捕获扩展Object
1 个错误
 */
