package com.design.pattern.adapter.classadapter;

/**
 * 适配器类
 * 继承了220 实现了5
 * 也就是说进去的是220 出来的是5 
 * 这里实现的是输出的那个电压
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        // 获取 output220V 电压 这里用的是继承
        int srcV = output220V();
        int dstV = srcV / 44; // 转换成5v 事实上还有一些更细致的逻辑
        return dstV;
    }
}
