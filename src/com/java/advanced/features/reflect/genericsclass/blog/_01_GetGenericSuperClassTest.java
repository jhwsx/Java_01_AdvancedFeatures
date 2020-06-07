package com.java.advanced.features.reflect.genericsclass.blog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author wangzhichao
 * @since 2020/6/7
 */
public class _01_GetGenericSuperClassTest {
    public static void main(String[] args) {
        Type type = IntegerHolder.class.getGenericSuperclass();
        System.out.println("type = " + type);
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                Class typeArgument = (Class) actualTypeArgument;
                System.out.println("替换泛型类型参数的实际类型为：" + typeArgument.getName());
            }
            // 获取声明了泛型类型的原始类型
            Type rawType = parameterizedType.getRawType();
            Class rawClass = (Class) rawType;
            System.out.println("声明了泛型类型的原始类型为：" + rawClass.getName());
        }
    }
}
