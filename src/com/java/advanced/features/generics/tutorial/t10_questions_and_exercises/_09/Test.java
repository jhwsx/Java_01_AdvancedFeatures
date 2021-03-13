package com.java.advanced.features.generics.tutorial.t10_questions_and_exercises._09;

/**
 * Will the following class compile? If not, why?
 *
 * public class Singleton<T> {
 *
 *     public static T getInstance() {
 *         if (instance == null)
 *             instance = new Singleton<T>();
 *
 *         return instance;
 *     }
 *
 *     private static T instance = null;
 * }
 *
 * 不能, 不能声明类型为类型参数的静态字段
 *
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {
}

//class Singleton<T> {
//
//    public static T getInstance() {
//        if (instance == null)
//            instance = new Singleton<T>();
//
//        return instance;
//    }
//
//    private static T instance = null;
//}
