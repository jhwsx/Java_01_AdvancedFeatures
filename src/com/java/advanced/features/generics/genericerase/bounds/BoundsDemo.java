package com.java.advanced.features.generics.genericerase.bounds;

interface Flyable {
    void fly();
}

class FlyPower<T extends Flyable> {
    T item;

    FlyPower(T item) {
        this.item = item;
    }

    void showFly() {
        item.fly();
    }
}

class Person {
    String name;
    int age;
}

class FlyPowerPerson<T extends Person & Flyable> {
    T item;

    FlyPowerPerson(T item) {
        this.item = item;
    }

    void showFly() {
        item.fly();
    }

    String getName() {
        return item.name;
    }

    int getAge() {
        return item.age;
    }
}

interface SuperHearing {
    void hearSubtleNoises();
}

interface SuperSmell {
    void trackBySmell();
}

interface SuperVision {
    void seeThroughWalls();
}

class SuperPowerPerson<T extends Person & Flyable & SuperHearing & SuperSmell & SuperVision> {
    T item;
    SuperPowerPerson(T item) {
        this.item = item;
    }

    void showFly() {
        item.fly();
    }

    String getName() {
        return item.name;
    }

    int getAge() {
        return item.age;
    }

    void showSuperSmell() {
        item.trackBySmell();
    }

    void showSuperHearing() {
        item.hearSubtleNoises();
    }

    void showSuperVision() {
        item.seeThroughWalls();
    }
}

class SuperMan extends Person implements Flyable, SuperVision, SuperHearing, SuperSmell {

    @Override
    public void fly() {
        System.out.println("fly");
    }

    @Override
    public void hearSubtleNoises() {
        System.out.println("hearSubtleNoises");
    }

    @Override
    public void trackBySmell() {
        System.out.println("trackBySmell");
    }

    @Override
    public void seeThroughWalls() {
        System.out.println("seeThroughWalls");
    }
}

public class BoundsDemo {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        superMan.name = "SuperMan";
        superMan.age = 18;
        SuperPowerPerson<SuperMan> superPowerPerson =
                new SuperPowerPerson<>(superMan);
        System.out.println(superPowerPerson.getAge());
        System.out.println(superPowerPerson.getName());
        superPowerPerson.showFly();
        superPowerPerson.showSuperHearing();
        superPowerPerson.showSuperSmell();
        superPowerPerson.showSuperVision();
    }
}
/*
打印结果：
18
SuperMan
fly
hearSubtleNoises
trackBySmell
seeThroughWalls
 */