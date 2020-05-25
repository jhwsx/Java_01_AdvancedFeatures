package com.java.advanced.features.reflect.genericsclass.type.genericarraytype;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * GenericArrayType
 *
 * 泛型数组,组成数组的元素中有范型则实现了该接口; 它的组成元素是ParameterizedType或TypeVariable类型,
 * 它只有一个方法:
 * <p>
 * Type getGenericComponentType(): 返回数组的组成对象
 */
public class Test {

    List<String>[] lists;

    public static void main(String[] args) throws NoSuchFieldException {
        // 获取 lists 对应的 Field
        Field listsField = Test.class.getDeclaredField("lists");
        // 获取 listsField 的泛型类型
        Type listsFieldGenericType = listsField.getGenericType(); // 其实是 GenericArrayType
        GenericArrayType genericArrayType = (GenericArrayType) listsFieldGenericType;
        System.out.println(genericArrayType); // java.util.List<java.lang.String>[]
        // 获取数组的组成对象
        Type genericComponentType = genericArrayType.getGenericComponentType(); // 其实是一个 ParameterizedType
        System.out.println(genericComponentType); // java.util.List<java.lang.String>
    }
}
