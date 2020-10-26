package com.abstractoop.exer;
/**
 * 抽象类的匿名子类
 * 
 * 为什么要使用匿名子类？
 * 因为有时候并不是重复利用的，使用匿名子类匿名对象，就是一次性的。
 * 
 */
public class AnonyAbstract {
    public static void main(String[] args) {
        // 匿名对象。为什么可以直接调用method，因为是static
        methodA(new Student()); 

        // 非匿名类 Worker
        // 匿名对象 没人知道啥对象
        Worker worker = new Worker();
        methodB(worker);
        // 为什么 methodB 就可以使用
        // 这里的worker是 Person的子类
        methodB(new Worker());

        // 创建了匿名子类的 非匿名对象 p
        Person p = new Person(){  
            @Override
            public void eat() {
                System.out.println("匿名子类&非匿名对象 eat");
            }

            @Override
            public void breathe() {
                System.out.println("匿名子类&非匿名对象 breathe");
            }
        };
        methodB(p); // 调用
        System.out.println("******************");

        // 匿名子类
        // 匿名对象
        methodB(new Person(){
            @Override
            public void eat() {
                System.out.println("匿名子类&匿名对象 eat");
            }

            @Override
            public void breathe() {
                System.out.println("匿名子类&匿名对象 breathe");
            }
        });

        // 匿名子类&非匿名对象 eat
        // 匿名子类&非匿名对象 breathe
        // ******************
        // 匿名子类&匿名对象 eat
        // 匿名子类&匿名对象 breathe
    }

    public static void methodA(Student s) {

    }
    public static void methodB(Person p) {
        p.eat();
        p.breathe();
    }
}

class Worker extends Person {

    // 这里是重写了所有的抽象类
    // eat是Person的
    // breathe是Creature的

    @Override
    public void eat() {
        
    }

    @Override
    public void breathe() {
        
    }
}