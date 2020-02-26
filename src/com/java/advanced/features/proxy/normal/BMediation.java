package com.java.advanced.features.proxy.normal;

import com.java.advanced.features.proxy.BRealEstateCompany;
import com.java.advanced.features.proxy.BSellHouseInterface;

/**
 * B 中介
 */
public class BMediation implements BSellHouseInterface {
    // 真实的对象
    private BRealEstateCompany company;

    public BMediation(BRealEstateCompany company) {
        this.company = company;
    }

    /*前置处理器*/
    private void doSomethingBefore() {
        System.out.println("帮您分析买房需求，找到最适合您的房子。");
    }
    /*后置处理器*/
    private void doSomethingAfter() {
        System.out.println("帮您搞定繁琐的贷款审批，让您购房无忧！");
    }
    @Override
    public void sellBHouse(float price) {
        doSomethingBefore();
        company.sellBHouse(price);
        doSomethingAfter();
    }
}
