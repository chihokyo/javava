package com.inheritancetext2.com;

public class Circle {

    // 属性
    private double radius;

    // 构造器
    public Circle() {
        this.radius = 1.0;
    }

    // 方法
    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double findArea() {
        // double area = Math.PI * radius * radius;
        // return area;
        return Math.PI * radius * radius;
    }

}
