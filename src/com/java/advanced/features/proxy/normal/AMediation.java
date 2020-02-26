package com.java.advanced.features.proxy.normal;

import com.java.advanced.features.proxy.ARealEstateCompany;
import com.java.advanced.features.proxy.ASellHouseInterface;

/**
 * A 中介
 */
public class AMediation implements ASellHouseInterface {
    /**
     * 持有的真实角色对象引用
     */
    private ARealEstateCompany company;

    public AMediation(ARealEstateCompany company) {
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
    public void sellAHouse(float size) {
        doSomethingBefore();
        company.sellAHouse(size);
        doSomethingAfter();
    }
}
