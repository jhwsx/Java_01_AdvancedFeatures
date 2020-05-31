package com.java.advanced.features.reflect.basicclass;

import com.java.advanced.features.reflect.Apple;
import com.java.advanced.features.reflect.Fruit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/5/31
 */
@Deprecated
public class _09_ClassIsXXXMethodTest {
    public static void main(String[] args) {
        // isAnnotation()  是不是注解类型
        System.out.println("Override.class.isAnnotation() = " + Override.class.isAnnotation());
        // isAnnotationPresent(Class<? extends Annotation> annotationClass)
        boolean annotationPresent = _09_ClassIsXXXMethodTest.class.isAnnotationPresent(Deprecated.class);
        System.out.println("annotationPresent = " + annotationPresent);
        // isAnonymousClass()  是不是匿名类
        System.out.println("new Serializable(){}.getClass().isAnonymousClass() = " + new Serializable() {
        }.getClass().isAnonymousClass());
        // isArray() 是不是数组
        System.out.println("String[].class.isArray() = " + String[].class.isArray());
        // isAssignableFrom(Class<?> cls)
        System.out.println("Collection.class.isAssignableFrom(List.class) = " + Collection.class.isAssignableFrom(List.class));
        // isEnum()
        System.out.println("Color.class.isEnum() = " + Color.class.isEnum());
        // isInstance(Object obj)
        List<String> list = new ArrayList<>();
        System.out.println("Collection.class.isInstance(list) = " + Collection.class.isInstance(list));
        // isInterface() 是不是接口
        System.out.println("Serializable.class.isInterface() = " + Serializable.class.isInterface());

        class A {

        }
        // isLocalClass() 是不是局部类，定义在方法内部的类
        System.out.println("A.class.isLocalClass() = " + A.class.isLocalClass());

        System.out.println("B.class.isMemberClass() = " + B.class.isMemberClass());
        // isPrimitive() 是不是原生类型
        System.out.println("int.class.isPrimitive() = " + int.class.isPrimitive());

        Apple apple = new Apple();
        Fruit fruit = new Fruit();
        System.out.println("Fruit.class.isInstance(apple) = " + Fruit.class.isInstance(apple));
        System.out.println("Fruit.class.isInstance(fruit) = " + Fruit.class.isInstance(fruit));
        System.out.println("apple instanceof Fruit = " + (apple instanceof Fruit));
        System.out.println("fruit instanceof Fruit = " + (fruit instanceof Fruit));

        boolean assignableFrom = Fruit.class.isAssignableFrom(Apple.class);
        System.out.println("assignableFrom = " + assignableFrom);
    }

    class B {

    }
}
