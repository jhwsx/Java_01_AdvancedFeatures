package com.java.advanced.features.generics.genericclass;


class Food {

}

class Fruit extends Food {

}

class Apple extends Fruit {

}

class Rice extends Food {

}

public class Holder<T> {
    private T a;

    public Holder(T a) {
        this.a = a;
    }

    public T get() {
        return a;
    }

    public void set(T a) {
        this.a = a;
    }

    public static void main(String[] args) {
        Holder<Fruit> fruitHolder = new Holder<>(new Fruit());
        fruitHolder.set(new Fruit());
        fruitHolder.set(new Apple());
        // 编译报错：需要的是 Fruit 类型，但是传入的是 Food 类型
        // fruitHolder.set(new Food());
        // 编译报错：需要的是 Fruit 类型，但是传入的是 Rice 类型
        // fruitHolder.set(new Rice());
    }
}
