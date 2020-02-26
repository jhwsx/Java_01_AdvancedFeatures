package com.java.advanced.features.proxy;

/**
 * B 房地产公司
 */
public class BRealEstateCompany implements BSellHouseInterface {
    @Override
    public void sellBHouse(float price) {
        System.out.println("这是一套价值为" + price + "万的房子。");
    }
}
