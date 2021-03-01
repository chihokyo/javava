package com.design.uml;
/**
 * 聚合关系
 * 是一种整体和部分的关系。关联关系的特例。
 * !!这里要和组合关系作为对比，这里的鼠标和显示器都是可以分离出来了
 * 所以具有导航性，多重性
 * 空心菱形
 */
public class Aggregation {
    
}

class ComputerAgg {
    private MouseAgg mouse;
    private MonitorAgg monitor;

    public void setMouse(MouseAgg mouse) {
        this.mouse = mouse;
    }
    public void setMonitor(MonitorAgg monitor) {
        this.monitor = monitor;
    }
}

class MouseAgg {}
class MonitorAgg {}