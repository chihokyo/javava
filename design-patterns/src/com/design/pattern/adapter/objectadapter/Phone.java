package com.design.pattern.adapter.objectadapter;

public class Phone {
    // 充电
    // 这里转入的是你要适配的接口
    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("OKK");
        } else if(iVoltage5V.output5V() > 5 ) {
            System.out.println("NOOOO!");
        }
    }
}