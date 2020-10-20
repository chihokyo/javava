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

    public double findVolume() {
        // return Math.PI * getRadius() * getRadius() * getLength();
        return getLength() * findArea();
    }
}
