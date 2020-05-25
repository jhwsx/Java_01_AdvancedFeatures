package com.java.advanced.features.reflect.genericsclass.type.wildcard;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * WildcardType
 *
 * 该接口表示通配符泛型, 比如? extends Number 和 ? super Integer 它有如下方法:
 *
 * Type[] getUpperBounds(): 获取范型变量的上界
 * Type[] getLowerBounds(): 获取范型变量的下界
 * 注意:
 *
 * 现阶段通配符只接受一个上边界或下边界, 返回数组是为了以后的扩展, 实际上现在返回的数组的大小是1
 */
public class Test {
    private List<? extends Number> a;
    private List<? super Integer> b;

    public static void main(String[] args) throws NoSuchFieldException {
        // 获取 Field 对象
        Field aField = Test.class.getDeclaredField("a");
        Field bField = Test.class.getDeclaredField("b");
        // 获取 Field 对应的泛型类型
        Type aFieldGenericType = aField.getGenericType(); // 其实是 ParameterizedType
        Type bFieldGenericType = bField.getGenericType(); // 其实是 ParameterizedType
        ParameterizedType aParameterizedType = (ParameterizedType) aFieldGenericType;
        ParameterizedType bParameterizedType = (ParameterizedType) bFieldGenericType;
        // 从泛型类型里获取通配符类型
        Type[] aActualTypeArguments = aParameterizedType.getActualTypeArguments();
        for (Type aActualTypeArgument : aActualTypeArguments) {
            WildcardType wildcardType = (WildcardType) aActualTypeArgument;
            Type[] upperBounds = wildcardType.getUpperBounds();
            for (Type upperBound : upperBounds) {
                Class clazz = (Class) upperBound;
                System.out.println("上界：" + clazz.getName());
            }
        }
        Type[] bActualTypeArguments = bParameterizedType.getActualTypeArguments();
        for (Type bActualTypeArgument : bActualTypeArguments) {
            WildcardType wildcardType = (WildcardType) bActualTypeArgument;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            for (Type lowerBound : lowerBounds) {
                Class clazz = (Class) lowerBound;
                System.out.println("下界：" + clazz.getName());
            }
        }
    }
}
