package com.java.advanced.features.reflect.genericsclass.blog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @author wangzhichao
 * @since 2020/6/7
 */
public class _03_TypeVariableTest {
    public static void main(String[] args) {
        // 获取此接口直接继承的接口对应的 Type 对象数组
        Type[] types = NumberGenerator.class.getGenericInterfaces();
        for (Type type : types) {
            System.out.println("type = " + type); // 打印：Generator<T>
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                // 获取替换了泛型类型参数的实际类型的 Type 对象数组
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                   if( actualTypeArgument instanceof TypeVariable) {
                       TypeVariable typeVariable = (TypeVariable) actualTypeArgument;
                       System.out.println("泛型类型参数的名字为 " + typeVariable.getName()); // 打印：T
                       Type[] bounds = typeVariable.getBounds();
                       for (Type bound : bounds) {
                           Class boundClass = (Class) bound;
                           System.out.println("泛型类型参数的上界为：" + boundClass.getName()); // 打印：java.lang.Number
                       }
                   }
                }
            }
        }
    }
}
