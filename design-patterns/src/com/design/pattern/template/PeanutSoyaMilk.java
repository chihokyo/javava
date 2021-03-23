package com.design.pattern.template;
/**
 * 模块方式
 * 花生豆乳
 */
public class PeanutSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("2 ADD Peanut ");
    }
}
