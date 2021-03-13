package com.design.pattern.adapter.objectadapter;

/**
 * 适配器类
 */
public class VoltageAdapter implements IVoltage5V {

    private Voltage220V voltage220v; // 关联关系 聚合

    // 构造器 传入实例
    public VoltageAdapter(Voltage220V voltage220v) {
        this.voltage220v = voltage220v;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (voltage220v != null) {
            int src = voltage220v.output220V();
            System.out.println("*****使用对象适配器 进行适配*****");
            dst = src / 44;
            System.out.println("适配完成，输出的电压为=" + dst);
        }
        return dst;
    }

}
