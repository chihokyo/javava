package com.inheritancetext2.com;

public class CylinderTest {
    public static void main(String[] args) {
        Cylinder cy = new Cylinder();
        cy.setRadius(2.2);
        cy.setLength(3.4);

        double volume = cy.findVolume();
        System.out.println("圆柱的体积是： " + volume);

        // 重写前
        // double area = cy.findArea();
        // System.out.println("圆的面积是： " + area);

        // 重写后
        double area = cy.findArea();
        System.out.println("圆柱的表面积是： " + area);
    }
}
