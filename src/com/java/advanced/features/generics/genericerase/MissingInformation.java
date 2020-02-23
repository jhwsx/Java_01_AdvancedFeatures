package com.java.advanced.features.generics.genericerase;

import java.lang.reflect.TypeVariable;
import java.util.*;

class Pair<First, Second> {
}

class Holder<T> {}
public class MissingInformation {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Pair<Integer, Integer> pair = new Pair<>();
        Holder<Double> holder = new Holder<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(pair.getClass().getTypeParameters()));
        TypeVariable<? extends Class<? extends Holder>>[] typeVariables = holder.getClass().getTypeParameters();
        System.out.println(Arrays.toString(typeVariables));
        for (TypeVariable<? extends Class<? extends Holder>> typeVariable : typeVariables) {
            System.out.println("typeVariable.getBounds() = " + Arrays.toString(typeVariable.getBounds()));
            System.out.println("typeVariable.getName() = " + typeVariable.getName());
            System.out.println("typeVariable.getGenericDeclaration() = " + typeVariable.getGenericDeclaration());
            System.out.println("typeVariable.getAnnotatedBounds() = " + Arrays.toString(typeVariable.getAnnotatedBounds()));
        }
    }
}
/*
打印结果：
[E]
[K, V]
[First, Second]
 */