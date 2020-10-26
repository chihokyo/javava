package com.abstractoop.exer;

import java.util.Calendar;
// import java.util.Scanner;

/**
 * 1个比较大的练习项目 工资系统
 * 
 * 定义PayrollSystem类，创建Employee变量数组并初始化，该数组存放各类雇员对象的引用。
 * 利用循环结构遍历数组元素，输出各个对象的类型,name,number,birthday。
 * 当键盘输入本月月份值时，如果本月是某个Employee对象的生日，还要输出增加工资信息。
 */

public class TemplateTest3 {
    public static void main(String[] args) {

        // 方式1：
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("请输入当月的月份：");
        // int month = scanner.nextInt();

        // 方式2：
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);

        EmployeeB[] emps = new EmployeeB[2];
        emps[0] = new SalariedEmployee("AMY", 10001, new MyDate(2002, 10, 2), 10000);
        emps[1] = new HourlyEmployee("TOM", 10002, new MyDate(1998, 10, 28), 60, 240);

        for (int i = 0; i < emps.length; i++) {
            System.out.println(emps[i]);
            double salary = emps[i].earnings();
            System.out.println("月工资是: " + salary);
            if ((month + 1) == emps[i].getBirthday().getMonth()) {
                System.out.println("生日快乐，补助1000");
            }
        }
    }
}

/**
 * * 定义一个Employee类，
 * 该类包含： private成员变量name,number,birthday，其中birthday 为MyDate类的对象；
 * abstract方法earnings()； 
 * toString()方法输出对象的name,number和birthday。
 */

// TIPS 因为同一个包有重名的类，所以修改了一下名字为EmployeeB
abstract class EmployeeB {
    private String name;
    private int number;
    private MyDate birthday;

    public EmployeeB(String name, int number, MyDate birthday) {
        super();
        this.name = name;
        this.number = number;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    public abstract double earnings();

    @Override
    public String toString() {
        return "name=" + name + ", number=" + number + ", birthday=" + birthday.toDateString();
    }
}

/**
 * MyDate类包含: 
 * private成员变量year,month,day
 * toDateString()方法返回日期对应的字符串：xxxx年xx月xx日
 */
class MyDate {

    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        super();
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String toDateString() {
        return year + "年" + month + "月" + day + "日";
    }
}

/**
 * * 定义SalariedEmployee类继承Employee类，
 * 实现按月计算工资的员工处理。该类包括：private成员变量monthlySalary；
 * 实现父类的抽象方法earnings(),该方法返回monthlySalary值；
 * toString()方法输出员工类型信息及员工的name，number,birthday。
 */
class SalariedEmployee extends EmployeeB {

    private double monthlySalary; // 月工资

    public SalariedEmployee(String name, int number, MyDate birthday) {
        super(name, number, birthday);
    }

    public SalariedEmployee(String name, int number, MyDate birthday, double monthlySalary) {
        super(name, number, birthday);
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double earnings() {
        return monthlySalary;
    }

    @Override
    public String toString() {
        // 调用父类的方法进行输出
        return "SalariedEmployee[" + super.toString() + "]";
    }
}

/*
 * 参照SalariedEmployee类定义HourlyEmployee类，
 * 实现按小时计算工资的员工处理。该类包括：
 * private成员变量wage和hour； 
 * 实现父类的抽象方法earnings(),该方法返回wage*hour值；
 * toString()方法输出员工类型信息及员工的name，number,birthday。
 */

class HourlyEmployee extends EmployeeB {

    private int wage;// 每小时的工资
    private int hour;// 月工作的小时数

    public HourlyEmployee(String name, int number, MyDate birthday) {
        super(name, number, birthday);
    }

    public HourlyEmployee(String name, int number, MyDate birthday, int wage, int hour) {
        super(name, number, birthday);
        this.wage = wage;
        this.hour = hour;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public double earnings() {
        return wage * hour;
    }

    @Override
    public String toString() {
        return "HourlyEmployee[" + super.toString() + "]";
    }

}