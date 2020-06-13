package com.java.advanced.features.jvm.klass._02;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * 弱引用演示
 * 弱引用用来描述非必需对象，当 JVM 进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象。
 * 也可以用来构建敏感数据的缓存，如用于生命周期更短的，对内存更敏感的场景中，比如占用内存很大的 Map，
 * Java 提供了 WeakHashMap。
 */
public class _03_WeakReferenceTest {
    static class User {
        public int id = 0;
        public String nanme = "";

        public User(int id, String nanme) {
            this.id = id;
            this.nanme = nanme;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", nanme='" + nanme + '\'' +
                    '}';
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }
    }
    public static void main(String[] args) {
        // 下面的是强引用
        User user = new User(1, "wzc");
        // 弱引用
        WeakReference<User> userWeakReference = new WeakReference<>(user);
        // 去掉强引用
        user = null;
        System.out.println("Before gc:");
        System.out.println(userWeakReference.get());
        System.gc();
        System.out.println("After gc:");
        System.out.println(userWeakReference.get());


    }
}

/*
 运行结果：
    Before gc:
    User{id=1, nanme='wzc'}
    After gc:
    null
 */
