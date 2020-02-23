package com.java.advanced.features.generics.genericerase.wildcard;

import java.util.ArrayList;
import java.util.List;

public class GenericAndContravarianceUseCase {
    public static void main(String[] args) {
        List<Fruit> fruitList = new ArrayList<>();
        collectApple(fruitList, new Apple());
        System.out.println(fruitList);
        List<Apple> appleList = new ArrayList<>();
        collectApple(appleList, new Apple());
        collectApple(appleList, new Hongfushi());
        System.out.println(appleList);
    }

    public static <T> void collectApple(List<? super T> list, T item) {
        list.add(item);
    }
}
