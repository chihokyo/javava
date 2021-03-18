package com.design.pattern.adapter.classadapter;

/**
 * 一个5V直流电接口 
 * 为什么是接口，我怀疑是可以用在所有的5V设备上
 */
public interface IVoltage5V {
    public int output5V();
}
