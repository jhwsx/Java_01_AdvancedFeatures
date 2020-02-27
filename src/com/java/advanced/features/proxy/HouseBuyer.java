package com.java.advanced.features.proxy;

import com.java.advanced.features.proxy.dynamic.MediationCompany;
import com.java.advanced.features.proxy.dynamic.ProxyUtils;
import com.java.advanced.features.proxy.normal.ABMediation;
import com.java.advanced.features.proxy.normal.AMediation;
import com.java.advanced.features.proxy.normal.BMediation;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HouseBuyer {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 1, 静态代理模式
//        // 小明买房，一个中介对应一个房地产公司（一对一）
//        ARealEstateCompany aRealEstateCompany = new ARealEstateCompany();
//        AMediation aMediation = new AMediation(aRealEstateCompany);
//        aMediation.sellAHouse(150f);
//        // 小花买房，一个中介对应一个房地产公司（一对一）
//        BRealEstateCompany bRealEstateCompany = new BRealEstateCompany();
//        BMediation bMediation = new BMediation(bRealEstateCompany);
//        bMediation.sellBHouse(30);
//        // 小明，小花买房，一个中介对应多个房地产公司（一对多）
//        ABMediation abMediation = new ABMediation(aRealEstateCompany, bRealEstateCompany);
//        abMediation.sellAHouse(180f);
//        abMediation.sellBHouse(40);

        // 2, 动态代理模式
        MediationCompany mediationCompany = new MediationCompany();
        // 小明买房
        ARealEstateCompany aRealEstateCompany = new ARealEstateCompany();
        mediationCompany.setRealEstateCompany(aRealEstateCompany);
        ASellHouseInterface consultant1 = (ASellHouseInterface) Proxy.newProxyInstance(
                aRealEstateCompany.getClass().getClassLoader(),
                new Class[] {ASellHouseInterface.class},
                mediationCompany);
//        System.out.println("consultant1 = " + consultant1.getClass());
        consultant1.sellAHouse(100f);
        // 小花买房
        BRealEstateCompany bRealEstateCompany = new BRealEstateCompany();
        mediationCompany.setRealEstateCompany(bRealEstateCompany);
        BSellHouseInterface consultant2 = (BSellHouseInterface) Proxy.newProxyInstance(
                bRealEstateCompany.getClass().getClassLoader(),
                new Class[] {BSellHouseInterface.class},
                mediationCompany);
//        System.out.println("consultant2 = " + consultant2.getClass());
        consultant2.sellBHouse(20);

        ProxyUtils.generateClassFile(consultant1.getClass().getSimpleName(),
                new Class[]{ASellHouseInterface.class});
        ProxyUtils.generateClassFile(consultant2.getClass().getSimpleName(),
                new Class[]{BSellHouseInterface.class});
        for (Method method : aRealEstateCompany.getClass().getMethods()) {
            System.out.println(method.getName());
        }
    }
}
/*
打印结果：
帮您分析买房需求，找到最适合您的房子。
这是一套面积为150.0平方的房子。
帮您搞定繁琐的贷款审批，让您购房无忧！
帮您分析买房需求，找到最适合您的房子。
这是一套价值为30.0万的房子。
帮您搞定繁琐的贷款审批，让您购房无忧！
帮您分析买房需求，找到最适合您的房子。
这是一套面积为180.0平方的房子。
帮您搞定繁琐的贷款审批，让您购房无忧！
帮您分析买房需求，找到最适合您的房子。
这是一套价值为40.0万的房子。
帮您搞定繁琐的贷款审批，让您购房无忧！

帮您分析买房需求，找到最适合您的房子。
这是一套面积为100.0平方的房子。
帮您搞定繁琐的贷款审批，让您购房无忧！
帮您分析买房需求，找到最适合您的房子。
这是一套价值为20.0万的房子。
帮您搞定繁琐的贷款审批，让您购房无忧！
 */
