package com.design.pattern.command;
/**
 * 点灯接收器
 * 这里去写具体的执行是怎么操作的
 * 最后还是要这里进行执行
 */
public class LigntReciever {
    public void on() {
        System.out.println("Light on");
    }

    public void off() {
        System.out.println("Light off");
    }
}
