package com.java.advanced.features.generics.tutorial.t07_wildcards._00;

import java.util.List;

/**
 * ? 是无界通配符，代表一个未知的类型，用于填充泛型形参
 * 无界通配符可以用在：方法参数的类型，字段的类型，局部变量的类型
 * 无界通配符不可以用在：泛型方法调用的类型实参，创建泛型类实例的类型实参，超类的类型实参。
 *
 * @author wangzhichao
 * @since 2021/3/3
 */
public class Test {
    public List<?> method(List<?> list) {

        List<?> list1; // OK

        // new A<?>(); // 报错：Wildcard type '?' cannot be instantiated directly

        // foo(?); // 报错
        return null;
    }

    List<?> list; // OK


    private <T> void foo(T t) {

    }


}

class A<T> {

}

//class B extends A<?> { // 报错：不可以使用通配符
//
//}
