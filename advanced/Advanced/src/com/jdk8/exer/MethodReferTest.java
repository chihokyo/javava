package com.jdk8.exer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
/**
 * 关于方法引用 Method Reference 结论：就是一个语法糖
 * 这个概念感觉还是蛮难的，解释起来。
 * lambda表达式的深层次的表达。
 * lambda表达式 不是函数是接口的实例吗。
 * 
 * 要求：实现接口的抽象方法的参数列表和返回值类型，必须与方法引用方法的参数列表和返回值类型保持一致。
 * 
 * 1.使用情境：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
 *
 * 2.方法引用，本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例。所以
 *   方法引用，也是函数式接口的实例。
 *
 * 3. 使用格式：  类(或对象) :: 方法名
 *
 * 4. 具体分为如下的三种情况：
 *    情况1     对象 :: 非静态方法
 *    情况2     类 :: 静态方法
 *
 *    情况3     类 :: 非静态方法
 *
 * 5. 方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的
 *    形参列表和返回值类型相同！（针对于情况1和情况2）
 * 
 */
public class MethodReferTest {
    public static void main(String[] args) {

        // 1 对象::实例方法
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("lalala");
        // 这里的方法引用就是
        // str -> System.out.println(str);
        // ps::println;
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("lalalala");

        Employee emp = new Employee(001, "Tom", 23, 9000);
        // Lambda方式
        Supplier<String> sup1 = () -> emp.getName();
        System.out.println(sup1.get()); // Tom
        // 方法引用
        Supplier<String> sup2 = emp::getName;
        System.out.println(sup2.get()); // Tom

        // 2 类::静态方法
        // Lambda方式
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(com1.compare(12, 100)); // -1
        // 方法引用
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(100, 10)); // 1

        Function<Double, Long> func = new Function<Double, Long>() {
            public Long apply(Double t) {
                return Math.round(t);
            };
        };
        System.out.println(func); // com.jdk8.exer.MethodReferTest$1@2c7b84de
        System.out.println(func.apply(10.8)); // 四舍五入 11

        Function<Double, Long> func1 = (d) -> Math.round(d);
        System.out.println(func1.apply(88.7)); // 89

        Function<Double, Long> func2 = Math::round;
        System.out.println(func2.apply(56.1)); // 56

        // 3 类::实例方法
        Comparator<String> comp1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(comp1.compare("bbb", "bbd")); // -2
        Comparator<String> comp2 = String::compareTo;
        System.out.println(comp2.compare("acb", "abd")); // 1

        BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pre1.test("abc", "abc")); // true
        BiPredicate<String, String> pre2 = String::equals;
        System.out.println(pre2.test("bbc", "bba")); // false

        Employee employee = new Employee(002, "jerry", 123, 10000);
        Function<Employee, String> fun1 = e -> e.getName();
        System.out.println(fun1.apply(employee)); // jerry
        Function<Employee, String> fun2 = Employee::getName;
        System.out.println(fun2.apply(employee)); // jerry



    }
}

// ********以下是测试辅助类 */

class Employee {

    private int id;
    private String name;
    private int age;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Employee id(int id) {
        this.id = id;
        return this;
    }

    public Employee name(String name) {
        this.name = name;
        return this;
    }

    public Employee age(int age) {
        this.age = age;
        return this;
    }

    public Employee salary(double salary) {
        this.salary = salary;
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
        return id == employee.id && Objects.equals(name, employee.name) && age == employee.age
                && salary == employee.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", age='" + getAge() + "'" + ", salary='"
                + getSalary() + "'" + "}";
    }

}

class EmployeeData {

    public static List<Employee> getEmployee() {

        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001, "马化腾", 34, 6000.38));
        list.add(new Employee(1002, "马云", 12, 9876.12));
        list.add(new Employee(1003, "刘强东", 33, 3000.82));
        list.add(new Employee(1004, "雷军", 26, 7657.37));
        list.add(new Employee(1005, "李彦宏", 65, 5555.32));
        list.add(new Employee(1006, "比尔盖茨", 42, 9500.43));
        list.add(new Employee(1007, "任正非", 26, 4333.32));
        list.add(new Employee(1008, "扎克伯格", 35, 2500.32));
        return list;
    }
}