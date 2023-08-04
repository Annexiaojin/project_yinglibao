package com.bj.springboot.api.pojo;

import com.bj.springboot.api.model.BidInfo;

import java.math.BigDecimal;

public class BidInfoView extends BidInfo {

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
