package com.objectarray.exer;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
/**
 *  创建该类的 5 个对象，并把这些对象放入 TreeSet 集合中（下一章：TreeSet 需使用泛型来定义）
 分别按以下两种方式对集合中的元素进行排序，并遍历输出：

 1). 使Employee 实现 Comparable 接口，并按 name 排序
 2). 创建 TreeSet 时传入 Comparator 对象，按生日日期的先后排序。
 */
public class TreeSetExer {
    public static void main(String[] args) {
        
        // 问题1 使用自然排序 compareTo 按照名字 return this.name.compareTo(e.name);
        TreeSet set = new TreeSet();

        Employee e1 = new Employee("liudehua",55,new MyDate(1965,5,4));
        Employee e2 = new Employee("zhangxueyou",43,new MyDate(1987,5,4));
        Employee e3 = new Employee("guofucheng",44,new MyDate(1987,5,9));
        Employee e4 = new Employee("liming",51,new MyDate(1954,8,12));
        Employee e5 = new Employee("liangzhaowei",21,new MyDate(1978,12,4));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }

        System.out.println("***********华丽的分割线***********");

        

        TreeSet set2 = new TreeSet(new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Employee && o2 instanceof Employee) {
                    Employee e1 = (Employee)o1;
                    Employee e2 = (Employee)o2;

                    MyDate b1 = e1.getBirthday();
                    MyDate b2 = e2.getBirthday();

                    // 方法1
                    // int minusYear = b1.getYear() - b2.getYear();
                    // if (minusYear != 0) {
                    //     return minusYear;
                    // }

                    // int minusMonth = b1.getMonth() - b1.getMonth();
                    // if (minusMonth != 0){
                    //     return minusMonth;
                    // }

                    // return b1.getDay() - b2.getDay();

                    // 方法2
                    return b1.compareTo(b2);

                }
                throw new RuntimeException("传入的数据类型不一致");
            }
        });

        Employee ee1 = new Employee("liudehua",55,new MyDate(1965,5,4));
        Employee ee2 = new Employee("zhangxueyou",43,new MyDate(1987,5,4));
        Employee ee3 = new Employee("guofucheng",44,new MyDate(1987,5,9));
        Employee ee4 = new Employee("liming",51,new MyDate(1954,8,12));
        Employee ee5 = new Employee("liangzhaowei",21,new MyDate(1978,12,4));

        set2.add(ee1);
        set2.add(ee2);
        set2.add(ee3);
        set2.add(ee4);
        set2.add(ee5);

        Iterator ite2 = set2.iterator();
        while (ite2.hasNext()) {
            System.out.println(ite2.next());
        }
    }
}


/**
 * 定义一个 Employee类
 */

class Employee implements Comparable {

    private String name;
    private int age;
    private MyDate birthday;

    public Employee() {
    }

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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

    public MyDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public Employee age(int age) {
        this.age = age;
        return this;
    }

    public Employee birthday(MyDate birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && age == employee.age && Objects.equals(birthday, employee.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, birthday);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            ", birthday='" + getBirthday() + "'" +
            "}";
    }

   @Override
   public int compareTo(Object o) {
       if (o instanceof Employee) {
           Employee e = (Employee)o;
           return this.name.compareTo(e.name);
       }

       throw new RuntimeException("传入类型不一致");
   }

}


/** 
 * MyDate 类
 * private成员变量year,month,day；并为每一个属性定义 getter, setter 方法
 */
class MyDate implements Comparable {

    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDate() {

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

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof MyDate){
            MyDate m = (MyDate)o;

            //比较年
            int minusYear = this.getYear() - m.getYear();
            if(minusYear != 0){
                return minusYear;
            }
            //比较月
            int minusMonth = this.getMonth() - m.getMonth();
            if(minusMonth != 0){
                return minusMonth;
            }
            //比较日
            return this.getDay() - m.getDay();
        }

        throw new RuntimeException("传入的数据类型不一致！");

    }
}