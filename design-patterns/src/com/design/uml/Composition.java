package com.design.uml;
/**
 * 组合关系
 * ！！！和聚合关系一起看！！一个可以分离不用new 一个不可分离
 * 她和聚合关系在下面就是说，不可分离。
 * 比如级联删除的时候
 * 
 * 实心菱形
 */
public class Composition {
    
}


class ComputerCom {
    // 这里就是一个组合，这个mouse 就是不可获取的
    private MouseCom mouse = new MouseCom();
    private MonitorCom monitor;

    public void setMouse(MouseCom mouse) {
        this.mouse = mouse;
    }
    public void setMonitor(MonitorCom monitor) {
        this.monitor = monitor;
    }
}

class MouseCom {}
class MonitorCom {}