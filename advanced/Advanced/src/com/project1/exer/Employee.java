package com.project1.exer;
/**
 * 父类 Employee 表示职位
 * 
 * @version
 * @author chin
 */
public class Employee {
    
    private int id; // 员工id
    private String name; // 员工姓名
    private int age; // 员工年龄

    public Employee() {
        
    }

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    private double salary;

    
    // protected 权限 本类，同一个包的类，继承类都可以。其他类不行。
    protected String getDetails() {
        return id + "\t" + name + "\t" + age + "\t" + salary + "\t";
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
