package com.java.advanced.features.generics.genericinterface;

class A {
    private static int count;
    private final int i = count++;

    @Override
    public String toString() {
        return "A " + i;
    }
}

class B {
    private static int count;
    private final int i = count++;

    @Override
    public String toString() {
        return "B " + i;
    }
}

public class GenericGeneratorImpl<T> implements Generator<T> {
    // 变量 type 是一个 Class 引用，它指向某个 Class 对象，比如 int.class, String.class 等
    private Class<T> type;

    public GenericGeneratorImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            // 注意如果 type 没有默认的构造器，那么此处调用它的 newInstance() 方法，会抛出异常。
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new GenericGeneratorImpl<>(type);
    }

    public static void main(String[] args) {
        Generator<A> generator = GenericGeneratorImpl.create(A.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(generator.next());
        }
        Generator<B> bGenerator = GenericGeneratorImpl.create(B.class);
        for (int i = 0; i < 3; i++) {
            System.out.println(bGenerator.next());
        }
    }
}

/*
打印结果：
A 0
A 1
A 2
A 3
A 4
B 0
B 1
B 2
 */
