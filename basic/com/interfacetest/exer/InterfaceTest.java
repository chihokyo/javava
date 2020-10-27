package com.interfacetest.exer;
/*
 * 接口的使用
 * 1.接口使用interface来定义
 * 2.Java中，接口和类是并列的两个结构
 * 3.如何定义接口：定义接口中的成员
 * 		
 * 		3.1 JDK7及以前：只能定义全局常量和抽象方法
 * 			>全局常量：public static final的.但是书写时，可以省略不写
 * 			>抽象方法：public abstract的
 * 			
 * 		3.2 JDK8：除了定义全局常量和抽象方法之外，还可以定义静态方法、默认方法（略）
 * 
 * 4. 接口中不能定义构造器的！意味着接口不可以实例化
 * 
 * 5. Java开发中，接口通过让类去实现(implements)的方式来使用.
 *    如果实现类覆盖了接口中的所有抽象方法，则此实现类就可以实例化 【这个和抽象类概念几乎相同】
 *    如果实现类没有覆盖接口中所有的抽象方法，则此实现类仍为一个抽象类
 *    
 * 6. Java类可以实现多个接口   --->弥补了Java单继承性的局限性
 *   格式：class AA extends BB implements CC,DD,EE
 *   
 * 7. 接口与接口之间可以继承，而且可以多继承
 * 
 * *******************************
 * 8. 接口的具体使用，体现多态性
 * 9. 接口，实际上可以看做是一种规范
 * 
 * 面试题：抽象类与接口有哪些异同？
 * 
 */
public class InterfaceTest {
    public static void main(String[] args) {
        
    }
}


interface Flyable {
    // 全局变量
    public static final int MAX_SPEED = 7900; // 宇宙第一速度
    int MIN_SPEED = 7900; // 什么都不写，默认就是上面的。省略了 public static final

    // 抽象方法
    public abstract void fly ();
    void stop (); // 同上 省略了 public abstract
    
    // Interfaces cannot have constructors
    // public Flyable(){} NG 接口不能定义构造器
}

interface Attackable {
    void attack();
}

class Plane implements Flyable {
    // 要么重写实现接口的所有方法
    // 要么把Plane变成抽象类
    // The type Plane must implement the inherited abstract method Flyable.stop()
    @Override
    public void fly() {
        System.out.println("Plane fly");
    }
    @Override
    public void stop() {
        System.out.println("Plane stop");
        
    }
}

abstract class Kite implements Flyable {
    // 虽然没有重写所有方法，
    // 但是成功的abstract类了
    @Override
    public void fly() {
    }
}

// Bullet 继承了 Object 又 实现了 Flyable, Attackable, CC 三个接口
// 而 CC 接口 继承了 AA BB 接口
class Bullet extends Object implements Flyable, Attackable, CC {

    @Override
    public void fly() {
        
    }
    @Override
    public void stop() {
        
    }
    @Override
    public void attack() {
        
    }
    @Override
    public void methodAA() {
        
    }
    @Override
    public void methodBB() {
        
    }
}

/********************************************************/

interface AA {
    void methodAA();
}
interface BB {
    void methodBB();
}
// 接口可以多重继承
interface CC extends AA, BB {
    
}