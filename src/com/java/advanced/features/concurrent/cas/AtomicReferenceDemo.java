package com.java.advanced.features.concurrent.cas;


import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        Person person = new Person("zhichao", 32);
        AtomicReference<Person> atomicReference = new AtomicReference<>(person);
        boolean result = atomicReference.compareAndSet(person, new Person("zhijie", 33));
        assert  result;
        System.out.println(atomicReference.get());
    }
    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
