package com.polymorphism.exer;
/**
 *  定义一个测试类PolyExer3
 * 编写equalsArea 方法测试2个对象面积是否相等（利用动态绑定技术）
 * 编写displayGeometricObject方法显示对方的面积（利用动态绑定技术）
 * 
 */
public class PolyExer3 {

    public static void main(String[] args) {

        PolyExer3 p = new PolyExer3();

        Circle c1 = new Circle(2.3, "white", 1.0);
        p.displayGeometricObject(c1); // 多态性，传入的是子类
        Circle c2 = new Circle(3.3, "white", 1.0);
        p.displayGeometricObject(c2); // 多态性，传入的是子类

        boolean isEquals = p.equalsArea(c1, c2);
        System.out.println("2个几何面积是否相等: " + isEquals);

        MyRectangle myrec = new MyRectangle(8.0, 1.2, "black", 2.0);
        p.displayGeometricObject(myrec);

    }

    // 判断2个几何体是否相等
    public boolean equalsArea(GeometricObject geo1, GeometricObject geo2) {
        return geo1.findArea() == geo2.findArea();
    }

    public void displayGeometricObject(GeometricObject geo) {
        System.out.println("面积为: " + geo.findArea());
    }

}

class GeometricObject {
    // 属性
    protected String color;
    protected double weight;

    // get/set
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // 构造器
    protected GeometricObject(String color, double weight) {
        super();
        this.color = color;
        this.weight = weight;
    }

    public double findArea() {
        return 0.0;
    }

}

class Circle extends GeometricObject {

    private double radius;

    // 因为父类没有空参构造器，默认调用super的时候就是没的，所以需要显示的进行定义
    public Circle(double radius, String color, double weight) {
        super(color, weight);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double findArea() {
        return Math.PI * radius * radius;
    }
}

class MyRectangle extends GeometricObject {

    private double width;
    private double height;

    public MyRectangle(double width, double height, String color, double weight) {
        super(color, weight);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double findArea() {
        return width * height;
    }
}
