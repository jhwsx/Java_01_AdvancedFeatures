package com.java.advanced.features.generics.genericerase.wildcard;

import java.util.ArrayList;
import java.util.List;

public class GenericsAndCovarianceUseCase {
    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple());
        appleList.add(new Apple());
        appleList.add(new Apple());
        traverseFruitList(appleList);
        List<Orange> orangeList = new ArrayList<>();
        orangeList.add(new Orange());
        orangeList.add(new Orange());
        traverseFruitList(orangeList);
    }

    public static void traverseFruitList(List<? extends Fruit> fruitList) {
        for (Fruit fruit : fruitList) {
            System.out.println(fruit);
        }
    }
}
