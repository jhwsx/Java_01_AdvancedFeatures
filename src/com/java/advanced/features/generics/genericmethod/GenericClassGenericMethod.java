package com.java.advanced.features.generics.genericmethod;

public class GenericClassGenericMethod<T> {
    private T x;

    public GenericClassGenericMethod(T x) {
        this.x = x;
    }
    // 这是个普通方法，只不是使用了泛型参数而已
    public T e(T t) {
        return t;
    }
    // 这是个泛型方法，因为它自己定义了泛型参数列表<T>
    // 请注意：这里的<T> 和 GenericClassGenericMethod 后面的<T> 没有任何关系
    // 但是泛型方法定义的泛型参数和所在类的泛型参数用一样的字母，是不好的写法。
    public <T> T f(T t) {
        return t;
    }
    // 这是个泛型方法，它自己定义了泛型参数列表<E>
    public <E> E g(E e) {
        return e;
    }
    // 这种写法是错误的，因为一个 static 的方法，无法访问泛型类的类型参数
//    public static T staticE(T t) {
//        return t;
//    }

    // 这是个静态泛型方法
    public static <F> F staticF(F f) {
        return f;
    }

    public static void main(String[] args) {
        GenericClassGenericMethod<String> genericClassGenericMethod = new GenericClassGenericMethod<>("Hello");
        // e() 方法是一个普通方法，它只允许使用创建 GenericClassGenericMethod 类时指定的类型参数的值：String
        System.out.println(genericClassGenericMethod.e("Hi"));
        // 编译报错：需要的是String类型，传入的是 int 类型。
        // System.out.println(genericClassGenericMethod.e(1));
        // f() 方法和 g() 方法是泛型方法，它们使用什么实际的类型来替换类型参数，由它们自己决定。
        System.out.println(genericClassGenericMethod.f(true));
        System.out.println(genericClassGenericMethod.<Boolean>f(false));
        System.out.println(genericClassGenericMethod.g("Hello"));
        System.out.println(genericClassGenericMethod.g(1f));
        System.out.println(genericClassGenericMethod.g(1.0d));
        // staticF() 是静态泛型方法
        System.out.println(GenericClassGenericMethod.staticF("staticF"));
    }
}
/*
打印结果：
Hi
true
false
Hello
1.0
1.0
staticF
 */