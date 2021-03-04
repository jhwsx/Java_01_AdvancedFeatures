package com.java.advanced.features.generics.tutorial.extra._05_generic_methods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/3/4
 */
public class Test {

    public static void main(String[] args) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<>();
        // T 类型被推断为 Object
        Test.<Object>fromArrayToCollection2(oa, co);

        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<>();
        // T 类型被推断为 String
        fromArrayToCollection2(sa, cs);

        // T 类型被推断为 Object
        fromArrayToCollection2(sa, co);

        Integer[] ia = new Integer[100];
        Float[] fa = new Float[100];
        Number[] na = new Number[100];
        Collection<Number> cn = new ArrayList<>();

        // T 类型被推断为 Number
        fromArrayToCollection2(ia, cn);
        // T 类型被推断为 Number
        fromArrayToCollection2(fa, cn);
        // T 类型被推断为 Number
        fromArrayToCollection2(na, cn);
        // T 类型被推断为 Object
        fromArrayToCollection2(na, co);

        // 编译报错
        // fromArrayToCollection2(sa, cn);

        List<String> src = new ArrayList<>();
        src.add("a");
        src.add("b");
        src.add("c");
        // https://www.cnblogs.com/yanggb/p/10436165.html
        List<String> dest = new ArrayList<>();
        dest.add(null);
        dest.add(null);
        dest.add(null);
        // List<? super T> dest 下边界通配符，保证在方法中，对 dest 只可以写，不可以读
        // List<? extends T> src 上边界通配符，保证在方法中，对 src 只可以读，不可以写
        Collections.copy(dest, src);
        System.out.println(dest);
    }

    static void fromArrayToCollection1(Object[] a, Collection<?> c) {
        for (Object o : a) {
            // c.add(o); // 编译报错，不可以添加元素。
        }
    }

    static <T> void fromArrayToCollection2(T[] a, Collection<T> c) {
        for (T t : a) {
            c.add(t);
        }
    }
}
