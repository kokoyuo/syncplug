package com.syncplug.beantrans;

import java.math.BigDecimal;

/**
 * Created by kokoyuo on 2018/1/19.
 */
public class SimpleValueBean {

    @TransFiled("userName")
    private String name;

    @TransFiled("userAge")
    private String age;

    @TransFiled("money")
    private BigDecimal money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
