package com.java.advanced.features.reflect.basicclass;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 获取某个类类型的所有接口: 自己直接实现的接口，父类直接实现的接口，父类的父类直接实现的接口...直至 Object 类。
 *
 * @author wangzhichao
 * @since 2020/5/25
 */
public class _05_GetAllInterfacesTest {
    public static void main(String[] args) {
//        List<Class<?>> list = new ArrayList<>();
//        getAllInterfaces(SuperHero.class, list);
//        printList(list);
//
//        list.clear();
//        getAllInterfaces(String.class, list);
//        printList(list);

        print(getAllInterfaces(SuperHero.class));
    }

    private static Class<?>[] getAllInterfaces(Class<?> clazz) {
        Class<?>[] result = null;
        // 先获取本类直接实现的接口数组，这个不会为null，顶多就是空数组。
        Class<?>[] interfacesSelf = clazz.getInterfaces();
        // 再获取父类直接实现的接口数组
        Class<?> superclass = clazz.getSuperclass(); // 获取超类，这个可以是 null
        Class<?>[] interfacesParent = null;
        if (superclass != null) {
            interfacesParent = getAllInterfaces(superclass);
        }
        // 最后把 interfacesSelf 和 interfacesParent 进行合并
        if (interfacesParent == null) {
            result = interfacesSelf;
        } else {
            int length = interfacesSelf.length + interfacesParent.length;
            result = new Class[length];
            System.arraycopy(interfacesSelf, 0, result, 0, interfacesSelf.length);
            System.arraycopy(interfacesParent, 0, result, interfacesSelf.length, interfacesParent.length);
        }
        return result;
    }

    private static void print(Class<?>[] interfaces) {
        for (Class<?> element : interfaces) {
            System.out.println(element.getName());
        }
        System.out.println();
    }
    // 这种写法不好，还需要传入一个容器参数
    /*private static <T> void getAllInterfaces(Class<T> clazz, List<Class<?>> list) {
        Class<?>[] interfaces = clazz.getInterfaces();
        Class<? super T> superclass = clazz.getSuperclass();
        if (superclass != null) {
            Class<?>[] interfaces1 = superclass.getInterfaces();
            if (interfaces1.length != 0) {
                list.addAll(Arrays.asList(interfaces1));
                for (Class<?> element : interfaces1) {
                    getAllInterfaces(element, list);
                }
            }
        }
        if (interfaces.length != 0) {
            list.addAll(Arrays.asList(interfaces));
            for (Class<?> element : interfaces) {
                getAllInterfaces(element, list);
            }
        }
    }

    private static void printList(List<Class<?>> list) {
        if (list.isEmpty()) {
            System.out.println("没有发现任何接口");
            return;
        }
        for (Class<?> element : list) {
            System.out.println(element);
        }
        System.out.println();
    }*/
}
