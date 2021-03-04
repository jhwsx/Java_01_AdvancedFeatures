package com.java.advanced.features.generics.tutorial.t09_restrictions_on_generics._06_cannot_create_catch_or_throw_objects_of_parameterized_types;

import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {

    public static <T extends Exception, J> void execute(List<J> jobs) {
//        try {
//            for (J job : jobs) {
//
//            }
//        } catch (T e) { // 编译报错： Cannot catch type parameters
//            e.printStackTrace();
//        }
    }

    public static <T extends Exception> void canThrowsTypeParameter() throws T {

    }
}
// 泛型类不可以直接地或间接地继承 Throwable。
//class MathException<T> extends  Throwable { // 编译报错：Generic class may not extend 'java.lang.Throwable'
//
//}

//class QueueFullException<T> extends Exception { // 编译报错：Generic class may not extend 'java.lang.Throwable'
//
//}


