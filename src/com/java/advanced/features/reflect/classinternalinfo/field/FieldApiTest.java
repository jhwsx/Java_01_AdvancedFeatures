package com.java.advanced.features.reflect.classinternalinfo.field;

import com.java.advanced.features.reflect.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldApiTest {
    public static void main(String[] args) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException, NoSuchFieldException {
        // 先创建一个 Person 对象
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getConstructor();
        Person person = constructor.newInstance();
        // 设置 name 属性的值为 “willwaywang6”
        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "willwaywang6");
        String name = (String) nameField.get(person);
        System.out.println("name = " + name);
        // 设置 salary 属性的值为 400000
        Field salaryField = personClass.getDeclaredField("salary");
        salaryField.set(person, 400000d);
        Float salary = (Float) salaryField.get(person);
        // 下边这行也是正确的
        // float salary = (float) salaryField.get(person);
        System.out.println("salary = " + salary);
    }
}
