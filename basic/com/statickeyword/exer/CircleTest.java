package com.statickeyword.exer;

/**
 * static 圆类 应用
 */
public class CircleTest {

    public static void main(String[] args) {

        Circle c1 = new Circle();
        Circle c2 = new Circle();
        Circle c3 = new Circle(2, 1);

        System.out.println("c1.id " + c1.getId());
        System.out.println("c2.id " + c2.getId());
        System.out.println("c3.id " + c3.getId());
        System.out.println("现在总共有圆的个数是: " + Circle.getTotal());
    }

}

class Circle {

    private double radius;
    private int id;

    private static int total;
    private static int init = 1001; // static 所有对象共享的 去掉之后所有对象都是1001 那就是错的了

    public double findArea() {
        return Math.PI * radius * radius;
    }

    public Circle() {
        id = init++; // 自增 多个对象共享
        total++;
    }

    public Circle(double radius, int id) {
        this();
        this.radius = radius;
        // id = init++;
        // total++; 这俩替换成 this()
    }

    public double getRadius() {
        return radius;
    }

    public int getId() {
        return id;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getTotal() {
        return total;
    }
}
