package com.java.advanced.features.reflect.genericsclass.type.parameterizedtype;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Map;

public class Test {
    Map<String, String> map;

    Class<?> clazz;

    public static void main(String[] args) throws NoSuchFieldException {
        Field mapField = Test.class.getDeclaredField("map");
        Type mapFieldGenericType = mapField.getGenericType();
        System.out.println("mapFieldGenericType = " + mapFieldGenericType); // java.util.Map<java.lang.String, java.lang.String>
        ParameterizedType parameterizedType = (ParameterizedType) mapFieldGenericType;
        Type rawType = parameterizedType.getRawType();
        Class rawTypeClass = (Class) rawType;
        System.out.println("声明泛型的类类型：" + rawTypeClass);
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            Class actualTypeArgumentClass = (Class) actualTypeArgument;
            System.out.println("替换了泛型类型参数的实际类型为：" + actualTypeArgumentClass.getName());
        }

        System.out.println("***** ***** ***** ***** ***** ***** ***** ");

        Field clazzField = Test.class.getDeclaredField("clazz");
        Type clazzFieldGenericType = clazzField.getGenericType();
        System.out.println("clazzFieldGenericType = " + clazzFieldGenericType);
        ParameterizedType clazzFieldParameterizedType = (ParameterizedType) clazzFieldGenericType;
        Type clazzFieldParameterizedTypeRawType = clazzFieldParameterizedType.getRawType();
        Class fieldParameterizedTypeRawType = (Class) clazzFieldParameterizedTypeRawType;
        System.out.println(fieldParameterizedTypeRawType);
        Type[] actualTypeArguments1 = clazzFieldParameterizedType.getActualTypeArguments();
        for (Type type : actualTypeArguments1) {
            WildcardType wildcardType = (WildcardType) type;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Type[] upperBounds = wildcardType.getUpperBounds();
            System.out.println("wildcardType = " + wildcardType);
            System.out.println("lowerBounds = " + Arrays.toString(lowerBounds));
            System.out.println("upperBounds = " + Arrays.toString(upperBounds));
        }
    }
}
