package com.java.advanced.features.generics.tutorial.t09_restrictions_on_generics._03_cannot_declare_static_fields_whose_types_are_type_parameters;

import com.sun.org.apache.xpath.internal.operations.And;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class MobileDevice<T> {
    // 编译报错
    // 'com.java.advanced.features.generics.tutorial.t09_restrictions_on_generics._03_cannot_declare_static_fields
    // _whose_types_are_type_parameters.MobileDevice.this' cannot be referenced from a static context
//     private static T os;

    public static void main(String[] args) {
        // 假定允许声明静态字段的类型为类型参数，那么因为 static 字段是被 android，ios，harmonyOS 对象所共享的，不能确定类型参数的实参到底是哪一个。
        MobileDevice<Android> android = new MobileDevice<>();
        MobileDevice<Ios> ios = new MobileDevice<>();
        MobileDevice<HarmonyOS> harmonyOS = new MobileDevice<>();
    }
}

class Android {

}

class Ios {

}

class HarmonyOS {

}
