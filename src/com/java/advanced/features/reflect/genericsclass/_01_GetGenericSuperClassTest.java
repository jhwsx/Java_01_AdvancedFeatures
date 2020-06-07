package com.java.advanced.features.reflect.genericsclass;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _01_GetGenericSuperClassTest {
    public static void main(String[] args) {
        Class<PointImpl> clazz = PointImpl.class;
        // 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type type = clazz.getGenericSuperclass(); // 获取 PointImpl.class 的超类
        System.out.println("type = " + type);
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            // 获取替换类型参数的实际类型对应的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                Class parameterArgClass = (Class) actualTypeArgument;
                System.out.println("替换泛型类的类型参数的实际类型为：" + parameterArgClass.getName()); // Integer
            }

            // 返回 Type 对象，表示声明此类型的类或接口。
            Type rawType = parameterizedType.getRawType();
            Class rawTypeClass = (Class) rawType;
            System.out.println("PointImpl 的父类类型：" + rawTypeClass.getName()); // Point
        }
        System.out.println(Arrays.toString(Point.class.getTypeParameters()));
    }
}
// Type 的 5 种类型
// Class             派生自 Object 的所有 Class 类
// ParameterizedType 泛型类型，如 Point<T>
// TypeVariable      泛型变量，如 Point 类里的 T x
// WildcardType      通配符，如 ?, ? extends Number
// GenericArrayType  数组， 如 String[]

/*
替换泛型类的类型参数的实际类型为：java.lang.Integer
PointImpl 的父类类型：com.java.advanced.features.reflect.genericsclass.Point
[T]
 */
