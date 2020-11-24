package com.jdk8.exer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
/**
 * Stream中间操作 这个本质上就是一个API来操作各种数据的， 感觉上很像以前写Web的时候各种ORM
 */
public class StreamAPITest2 {
    public static void main(String[] args) {
        // 筛选和切片
        // filter(Predicate p) 一般接收Lambda 从流中排除某些元素
        List<Employee> list = EmployeeData.getEmployee();
        Stream<Employee> stream = list.stream();
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println("*****华丽的分割线*****");
        // limit(n)——截断流，使其元素不超过给定数量。
        list.stream().limit(3).forEach(System.out::println);
        System.out.println("*****华丽的分割线*****");
        // skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        list.stream().skip(3).forEach(System.out::println);

        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 41, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));

        System.out.println("*****1华丽的分割线*****");
        // System.out.println(list);
        // distinct()——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        list.stream().distinct().forEach(System.out::println);

        // 映射
        // map(Function f)——接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> listArr = Arrays.asList("aa", "bb", "cc", "dd");
        listArr.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        // 获取员工姓名长度大于3的员工姓名
        List<Employee> employees = EmployeeData.getEmployee();
        Stream<String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println("*****华丽的分割线*****");
        Stream<Stream<Character>> streamStream = listArr.stream().map(StreamAPITest2::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });
        // flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        Stream<Character> characterStream = listArr.stream().flatMap(StreamAPITest2::fromStringToStream);
        characterStream.forEach(System.out::println);

        // 排序
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(6);
        list2.add(7);
        list2.add(8);
        list2.add(9);

        list1.addAll(list2);
        System.out.println(list1); // [1, 2, 3, 4, 6, 7, 8, 9]

        List<Integer> list3 = Arrays.asList(12, 5, -8, 99, 3);
        list3.stream().sorted().forEach(System.out::println); // -8,3,5,12,99

        List<Employee> employeeList = EmployeeData.getEmployee();
        // Employee cannot be cast to class java.lang.Comparable
        // java.lang.ClassCastException:
        // 抛异常，因为 Employee 没有实现 Comparable接口
        // employeeList.stream().sorted().forEach(System.out::println);

        // 先按照年龄从小到大排序，然后按照工作从高到低
        employeeList.stream().sorted((e1, e2) -> {
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            if (ageValue != 0) {
                return ageValue;
            } else {
                return -Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);

    }

    // 将字符串多个字符构成的集合转换成对应的Stream实例
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : list) {
            list.add(c);
        }
        return list.stream();
    }
}
