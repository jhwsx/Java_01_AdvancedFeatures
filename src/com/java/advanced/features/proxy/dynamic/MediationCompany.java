package com.java.advanced.features.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MediationCompany implements InvocationHandler {
    // 真实的对象
    private Object realEstateCompany;

    public void setRealEstateCompany(Object realEstateCompany) {
        this.realEstateCompany = realEstateCompany;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke(): proxy = " + proxy.getClass() + ", method = " + method + ", args = " + Arrays.toString(args));
        doSomethingBefore();
        Object result = method.invoke(realEstateCompany, args);
        doSomethingAfter();
        return result;
    }

    /*前置处理器*/
    private void doSomethingBefore() {
        System.out.println("帮您分析买房需求，找到最适合您的房子。");
    }

    /*后置处理器*/
    private void doSomethingAfter() {
        System.out.println("帮您搞定繁琐的贷款审批，让您购房无忧！");
    }
}
