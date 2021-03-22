package com.design.pattern.facade;

public class Projector {
   // 这里使用的是单例模式
   private static Projector instance = new Projector();
   public static Projector getInstance() {
       return instance;
   }
   public void on() {
       System.out.println("Projector on");
   }
   public void off() {
       System.out.println("Projector off");
   }
   public void up() {
       System.out.println("Projector up");
   } 
}
