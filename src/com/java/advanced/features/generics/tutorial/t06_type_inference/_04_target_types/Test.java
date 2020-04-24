package com.java.advanced.features.generics.tutorial.t06_type_inference._04_target_types;

import java.util.Collections;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Test {
    public static void main(String[] args) {
        /*
        public static final <T> List<T> emptyList() {
            return (List<T>) EMPTY_LIST;
        }
         */
        // 这种赋值操作，因为左边我们已经指定了类型实参是 String，
        // 所以编译器了解到这个信息，帮我们推断出方法调用的类型参数。
        List<String> listOne = Collections.emptyList();

        List<String> listTwo = Collections.<String>emptyList();
        // 在 Java8中下面的代码是正确的，但是在 Java7中，却是编译错误的。
        processStringList(Collections.emptyList());
        // 在 Java7中必须写成下面这样
        processStringList(Collections.<String>emptyList());
    }

    static void processStringList(List<String> stringList) {
        System.out.println(stringList);
    }
}
