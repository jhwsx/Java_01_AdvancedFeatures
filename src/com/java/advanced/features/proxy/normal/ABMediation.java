package com.java.advanced.features.proxy.normal;

import com.java.advanced.features.proxy.ARealEstateCompany;
import com.java.advanced.features.proxy.ASellHouseInterface;
import com.java.advanced.features.proxy.BRealEstateCompany;
import com.java.advanced.features.proxy.BSellHouseInterface;

/**
 * A & B 中介
 */
public class ABMediation implements ASellHouseInterface, BSellHouseInterface {
    // 真实的对象
    private ARealEstateCompany aCompany;
    private BRealEstateCompany bCompany;

    public ABMediation(ARealEstateCompany aCompany, BRealEstateCompany bCompany) {
        this.aCompany = aCompany;
        this.bCompany = bCompany;
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
        aCompany.sellAHouse(size);
        doSomethingAfter();
    }

    @Override
    public void sellBHouse(float price) {
        doSomethingBefore();
        bCompany.sellBHouse(price);
        doSomethingAfter();
    }
}
