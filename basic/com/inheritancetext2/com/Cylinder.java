package com.inheritancetext2.com;

public class Cylinder extends Circle {

    // 属性
    private double length;
    
    // 构造器
    public Cylinder() {
        this.length = 1.0;
    }

    // 方法
    public void setLength(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }
    // 圆柱体积
    public double findVolume() {
        return Math.PI * getRadius() * getRadius() * getLength();
        // return getLength() * findArea(); 因为已经重写了下面的方法，所以这里不能直接调用findArea
    }

    // 返回圆柱表面积
    @Override
    public double findArea() {
        return Math.PI * getRadius() * getRadius() * 2 + 2 * Math.PI * getLength() * getRadius();
        
    }
}
