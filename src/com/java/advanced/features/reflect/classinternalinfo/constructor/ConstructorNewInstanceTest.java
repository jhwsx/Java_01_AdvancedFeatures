package com.java.advanced.features.reflect.classinternalinfo.constructor;

import com.java.advanced.features.reflect.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorNewInstanceTest {
    public static void main(String[] args) {
        // 首先，获取到 private Person(String name, float salary) 对应的 Constructor 对象
        Class<Person> personClass = Person.class;
        try {
            Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class, float.class);
            // 因为目标构造方法是 private 的，所以需要设置下边的代码为 true。
            declaredConstructor.setAccessible(true);
            Person person = declaredConstructor.newInstance("willwaywang6", 18f);
            // 上面一行等价于下面一行
            // Person person = declaredConstructor.newInstance("willwaywang6", Float.valueOf(18f));
            // 错误写法演示1: 参数顺序写错，抛出：java.lang.IllegalArgumentException: argument type mismatch
            // declaredConstructor.newInstance(Float.valueOf(18f), "willwaywang6");
            // 错误写法演示2：参数个数写错，抛出: java.lang.IllegalArgumentException: argument type mismatch
            // declaredConstructor.newInstance("willwaywang6");
            System.out.println(person);
            System.out.println("name = " + person.getName() + ", salary = " + person.salary);
        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
/*
打印结果：
com.java.advanced.features.reflect.Person@4554617c
name = willwaywang6, salary = 18.0
 */
