package com.java.advanced.features.reflect.basicclass;

import com.java.advanced.features.reflect.Fruit;
import com.java.advanced.features.reflect.basicclass.OuterClass.StaticInnerClass;
import java.io.Serializable;

/**
 * https://stackoverflow.com/questions/15202997/what-is-the-difference-between-canonical-name-simple-name-and-class-name-in-jav
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _02_GetClassNamePackageNameTest {
    public static void main(String[] args) {
        // getName()返回的是虚拟机里面的class的表示，而getCanonicalName()返回的是更容易理解的表示。
        // getName() 是动态加载类所需要的，例如用于 Class.forName();
        // getCanonicalName() 用于获取 import 的名字
        // getTypeName() 不是数组，就调用 getName()；是数组，就是类名后面跟维数（[]）,几维就跟几个[]
        // 对于数组和内部类，两者打印有区别。
        Class<OuterClass> outerClassClass = OuterClass.class;
        Class<OuterClass.StaticInnerClass> staticInnerClassClass = OuterClass.StaticInnerClass.class;

        print(outerClassClass, "outerClassClass");

        print(staticInnerClassClass, "staticInnerClassClass");

        // 对于数组
        String[] array = new String[]{};
        Class<? extends String[]> arrayClass = array.getClass();
        print(arrayClass, "arrayClass");

        //
        Class<? extends Serializable> anonymousInnerClass = new Serializable() {
        }.getClass();
        print(anonymousInnerClass, "anonymousInnerClass");
    }

    private static <T> void print(Class<T> clazz, String label) {
        System.out.println(label + ".getName() = " + clazz.getName());
        System.out.println(label + ".getSimpleName() = " + clazz.getSimpleName());
        System.out.println(label + ".getCanonicalName() = " + clazz.getCanonicalName());
        System.out.println(label + ".getTypeName() = " + clazz.getTypeName());
        Package clazzPackage = clazz.getPackage();
        if (clazzPackage != null) {
            System.out.println(label +" packageName = " + clazzPackage.getName());
        }
        System.out.println();
    }
}
/*
打印结果：
outerClassClass.getName() = com.java.advanced.features.reflect.basicclass.OuterClass
outerClassClass.getSimpleName() = OuterClass
outerClassClass.getCanonicalName() = com.java.advanced.features.reflect.basicclass.OuterClass
outerClassClass.getTypeName() = com.java.advanced.features.reflect.basicclass.OuterClass
outerClassClass packageName = com.java.advanced.features.reflect.basicclass

staticInnerClassClass.getName() = com.java.advanced.features.reflect.basicclass.OuterClass$StaticInnerClass
staticInnerClassClass.getSimpleName() = StaticInnerClass
staticInnerClassClass.getCanonicalName() = com.java.advanced.features.reflect.basicclass.OuterClass.StaticInnerClass
staticInnerClassClass.getTypeName() = com.java.advanced.features.reflect.basicclass.OuterClass$StaticInnerClass
staticInnerClassClass packageName = com.java.advanced.features.reflect.basicclass

arrayClass.getName() = [Ljava.lang.String;
arrayClass.getSimpleName() = String[]
arrayClass.getCanonicalName() = java.lang.String[]
arrayClass.getTypeName() = java.lang.String[]

anonymousInnerClass.getName() = com.java.advanced.features.reflect.basicclass._02_GetClassNamePackageNameTest$1
anonymousInnerClass.getSimpleName() =
anonymousInnerClass.getCanonicalName() = null
anonymousInnerClass.getTypeName() = com.java.advanced.features.reflect.basicclass._02_GetClassNamePackageNameTest$1
anonymousInnerClass packageName = com.java.advanced.features.reflect.basicclass
 */