package com.java.advanced.features.reflect.genericsclass.type.genericarraytype;

import java.lang.reflect.*;
import java.util.List;

/**
 * GenericArrayType
 * GenericArrayType 表示一种数组类型，其组件类型为参数化类型或类型变量。
 * 泛型数组,组成数组的元素中有范型则实现了该接口; 它的组成元素是ParameterizedType或TypeVariable类型,
 * 它只有一个方法:
 * <p>
 * Type getGenericComponentType(): 返回数组的组成对象
 */
public class Test<T> {

    List<String>[] lists;

    T[] array;

    public static void main(String[] args) throws NoSuchFieldException {
        // 获取 lists 对应的 Field
        Field listsField = Test.class.getDeclaredField("lists");
        // 获取 listsField 的泛型类型 返回一个 Type 对象，它表示此 Field 对象所表示字段的声明类型。
        Type listsFieldGenericType = listsField.getGenericType(); // 其实是 GenericArrayType
        GenericArrayType genericArrayType = (GenericArrayType) listsFieldGenericType;
        System.out.println(genericArrayType); // java.util.List<java.lang.String>[]
        // 获取数组的组成对象
        Type genericComponentType = genericArrayType.getGenericComponentType(); // 其实是一个 ParameterizedType
        ParameterizedType parameterizedType = (ParameterizedType) genericComponentType;
        System.out.println(parameterizedType); // java.util.List<java.lang.String>

        System.out.println("***** ***** ***** ***** ***** ***** ***** ");

        Field arrayField = Test.class.getDeclaredField("array");
        Type arrayFieldType = arrayField.getGenericType();
        GenericArrayType arrayFieldGenericType = (GenericArrayType) arrayFieldType;
        System.out.println(arrayFieldGenericType);
        Type arrayGenericComponentType = arrayFieldGenericType.getGenericComponentType();
        TypeVariable<Class<String>> typeVariable = (TypeVariable<Class<String>>) arrayGenericComponentType;
        System.out.println(typeVariable);
    }
}
