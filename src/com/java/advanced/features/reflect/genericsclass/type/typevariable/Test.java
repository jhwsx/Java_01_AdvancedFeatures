package com.java.advanced.features.reflect.genericsclass.type.typevariable;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * TypeVariable
 * 泛型信息在编译时会被转换位一个特定的类型，而 TypeVariable 就是用来反映在 JVM 编译该泛型前的信息，
 * TypeVariable 就是 <K extends Serializable & Comparable, V> 的 K, V 类型变量
 *
 * Type[] getBounds(): 获取类型变量的上边界, 若未明确声明上边界则默认为Object
 * D getGenericDeclaration(): 获取声明该类型变量的类型
 * String getName(): 获取在源码中定义时的名字
 */
public class Test<K extends Serializable & Comparable, V> {
    K key;
    V value;

    public static void main(String[] args) throws NoSuchFieldException {
        Class<Test> clazz = Test.class;
        // 获取字段的 Field 对象
        Field keyField = clazz.getDeclaredField("key");
        Field valueField = clazz.getDeclaredField("value");
        // 获取 Field 的泛型类型
        Type keyFieldGenericType = keyField.getGenericType(); // 其实是 TypeVariable 类型
        Type valueFieldGenericType = valueField.getGenericType(); // 其实是 TypeVariable 类型
        TypeVariable keyTypeVariable = (TypeVariable) keyFieldGenericType;
        TypeVariable valueTypeVariable = (TypeVariable) valueFieldGenericType;

        String keyTypeVariableName = keyTypeVariable.getName();
        String valueTypeVariableName = valueTypeVariable.getName();
        System.out.println("key字段对应的泛型类型变量的名字：" + keyTypeVariableName); // K
        System.out.println("value字段对应的泛型类型变量的名字：" + valueTypeVariableName); // V
        // 获取声明了泛型变量的类
        System.out.println(keyTypeVariable.getGenericDeclaration()); // class com.java.advanced.features.reflect.genericsclass.type.typevariable.Test
        System.out.println(valueTypeVariable.getGenericDeclaration()); // class com.java.advanced.features.reflect.genericsclass.type.typevariable.Test
        // 获取上边界
        Type[] keyTypeVariableBounds = keyTypeVariable.getBounds();
        for (Type keyTypeVariableBound : keyTypeVariableBounds) {
            Class keyTypeVariableBoundClass = (Class) keyTypeVariableBound;
            System.out.println("K 的上边界：" + keyTypeVariableBoundClass.getName());
        }
        Type[] valueTypeVariableBounds = valueTypeVariable.getBounds();
        for (Type valueTypeVariableBound : valueTypeVariableBounds) {
            Class valueTypeVariableBoundClass = (Class) valueTypeVariableBound;
            System.out.println("V 的上边界：" + valueTypeVariableBoundClass.getName());
        }
    }
}

/*
key字段对应的泛型类型变量的名字：K
value字段对应的泛型类型变量的名字：V
class com.java.advanced.features.reflect.genericsclass.type.typevariable.Test
class com.java.advanced.features.reflect.genericsclass.type.typevariable.Test
K 的上边界：java.io.Serializable
K 的上边界：java.lang.Comparable
V 的上边界：java.lang.Object
 */
