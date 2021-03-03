package com.java.advanced.features.generics.tutorial.t04_bounded_type_parameters._00_bound;

/**
 * 限定的类型参数可以限定用于实例化泛型类型的类型。
 *
 * @author wangzhichao
 * @since 2020/4/23
 */
public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    // U extends Number 这就是限定的类型参数，其中 extends 是被重用了，在这里意思更广泛，表示类中的 extends 或接口中的 implements。
    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        integerBox.set(new Integer(10));
        // integerBox.inspect("some text"); // 编译报错：推断类型不符合上限
    }
}

/*
Error:(26, 20) java: 无法将类 com.java.advanced.features.generics.tutorial.t04_bounded_type_parameters._00_bound.Box<T>中的方法 inspect应用到给定类型;
  需要: U
  找到: java.lang.String
  原因: 推断类型不符合上限
    推断: java.lang.String
    上限: java.lang.Number
 */
