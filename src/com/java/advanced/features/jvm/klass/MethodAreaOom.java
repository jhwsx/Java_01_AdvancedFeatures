package com.java.advanced.features.jvm.klass;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 演示方法区的内存溢出
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * -XX:MetaspaceSize=10M 设置方法区的初始值为 10M，-XX:MaxMetaspaceSize=10M 设置方法区的最大值为 10M.
 * 这里使用了 cglib 动态生成
 * Enhancer中 setSuperClass和setCallback, 设置好了SuperClass后, 可以使用create制作代理对象了
 */
public class MethodAreaOom {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(TestObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects,
                                        MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });
            enhancer.create();
        }
    }

    static class TestObject {
        private double a = 34.53;
        private Integer b = 9999999;
    }
}
