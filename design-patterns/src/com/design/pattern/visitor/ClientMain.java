package com.design.pattern.visitor;

public class ClientMain {
    public static void main(String[] args) {
        ObjectStrucure objectStrucure = new ObjectStrucure();
        objectStrucure.attach(new Man());
        objectStrucure.attach(new Woman());
        
        Good good = new Good();
        Bad bad = new Bad();
        Waiting waiting = new Waiting();

        objectStrucure.display(good);;
        objectStrucure.display(bad);;
        objectStrucure.display(waiting);;
    }
}
