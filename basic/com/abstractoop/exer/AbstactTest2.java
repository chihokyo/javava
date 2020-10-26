package com.abstractoop.exer;
/**
 * 一个练习
 * 编写一个 Employee 类 声明为抽象类
 * 包含三个属性 name,id,salary
 * 提供必要的构造器和抽象方法 work()
 * 
 * Manager 既是员工，又有bonus属性
 * 
 * CommonEmployee 重写干活
 */
public class AbstactTest2 {
    public static void main(String[] args) {
        // 这里使用的是多态
        // 实例化的是一个子类，但是使用的是父类的类型
        Employee manager = new Manager("Amy", 1001, 5000, 50000);
        manager.work(); // Manager Work
    }
}

abstract class Employee {

    private String name;
    private int id;
    private double salary;

    public Employee() {
        super();
    }

    public Employee(String name, int id, double salary) {
        super();
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public abstract void work();

}

class Manager extends Employee {
    
    private double bonus;

    public Manager(double bonus) {
        super();
        this.bonus = bonus;
    }

    public Manager(String name, int id, double salary, double bonus) {
        super(name, id, salary);
        this.bonus = bonus;
    }

    @Override
    public void work() {
        System.out.println("Manager Work");
    }
}

class CommonEmployee extends Employee {

    @Override
    public void work() {
        System.out.println("CommonEmployee Work");

    }
}
