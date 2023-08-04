package com.bj.springboot.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BidInfoProduct implements Serializable {
    private String phone;
    private BigDecimal money;
    private String time;

    public BidInfoProduct() {
    }

    public BidInfoProduct(String phone, BigDecimal money, String time) {
        this.phone = phone;
        this.money = money;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
