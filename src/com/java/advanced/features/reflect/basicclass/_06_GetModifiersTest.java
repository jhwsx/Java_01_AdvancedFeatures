package com.java.advanced.features.reflect.basicclass;

import java.lang.reflect.Modifier;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _06_GetModifiersTest {
    public static void main(String[] args) {
        // 对于 com.java.advanced.features.reflect.basicclass.OuterClass.StaticInnerClass
        Class<OuterClass.StaticInnerClass> clazz = OuterClass.StaticInnerClass.class;
        int modifiers = clazz.getModifiers();
        System.out.println("modifiers = " + modifiers);
        System.out.println("Modifier.toString(modifiers) = " + Modifier.toString(modifiers));
        System.out.println("Modifier.isPublic(modifiers) = " + Modifier.isPublic(modifiers));
        System.out.println("Modifier.isStatic(modifiers) = " + Modifier.isStatic(modifiers));
        System.out.println("Modifier.isFinal(modifiers) = " + Modifier.isFinal(modifiers));
        System.out.println();
        // 对于 AbstractCollection
        Class<AbstractCollection> abstractCollectionClass = AbstractCollection.class;
        int modifiers1 = abstractCollectionClass.getModifiers();
        System.out.println("Modifier.toString(modifiers1) = " + Modifier.toString(modifiers1));
        System.out.println("Modifier.isAbstract(modifiers1) = " + Modifier.isAbstract(modifiers1));
        System.out.println();
        // 对于接口 List
        Class<List> listClass = List.class;
        int modifiers2 = listClass.getModifiers();
        System.out.println("Modifier.toString(modifiers2) = " + Modifier.toString(modifiers2));
        System.out.println("Modifier.isInterface(modifiers2) = " + Modifier.isInterface(modifiers2));

        System.out.println();

        int modifiers3 = OuterInterface.InnerInterface.class.getModifiers();
        System.out.println("modifiers3 = " + modifiers3);
        System.out.println("Modifier.toString(modifiers3) = " + Modifier.toString(modifiers3));
        System.out.println("Modifier.isPublic(modifiers3) = " + Modifier.isPublic(modifiers3));
        System.out.println("Modifier.isAbstract(modifiers3) = " + Modifier.isAbstract(modifiers3));
        System.out.println("Modifier.isStatic(modifiers3) = " + Modifier.isStatic(modifiers3));
        System.out.println("Modifier.isInterface(modifiers3) = " + Modifier.isInterface(modifiers3));

    }
}
