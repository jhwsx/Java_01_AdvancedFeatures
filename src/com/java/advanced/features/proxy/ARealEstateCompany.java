package com.java.advanced.features.proxy;

/**
 * A 房地产公司
 */
public class ARealEstateCompany implements ASellHouseInterface {
    @Override
    public void sellAHouse(float size) {
        System.out.println("这是一套面积为" + size + "平方的房子。");
    }
}
