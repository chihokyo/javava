package com.jdk8.exer;

import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream终止操作
 */

public class StreamAPITest3 {
    public static void main(String[] args) {
        /**
         * 1 匹配与查找
         */
        List<Employee> employees = EmployeeData.getEmployee();
        System.out.println(employees);
        // 1 是否所有员工的年龄都大于18
        // allMatch(Predicate<? super Employee> predicate)
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.err.println(allMatch); // false

        // 2 是否存在员工的工资大于10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch); // false

        // 3 是否存在员工姓“马”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("feng"));
        System.out.println(noneMatch); // true 有就是false 没有就是true

        // 4 返回第一个元素
        Optional<Employee> employeeOp = employees.stream().findFirst();
        System.out.println(employeeOp); // Optional[{ id='1001', name='马化腾', age='34', salary='6000.38'}]

        // 5 返回当前流的任意元素
        Optional<Employee> employeeRan = employees.parallelStream().findAny();
        System.out.println(employeeRan); // Optional[{ id='1006', name='比尔盖茨', age='42', salary='9500.43'}]

        // 6 返回流中的总数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count); // 5

        // 7 返回最大值
        // max(Comparator c)——返回流中最大值
        Stream<Double> salaryStream = employeeOp.stream().map(e -> e.getSalary());
        Optional<Double> maxSalary = salaryStream.max(Double::compare);
        System.out.println(maxSalary); // Optional[6000.38]

        // 8 返回最小值
        // min(Comparator c)——返回流中最小值
        Optional<Employee> minSalary = employees.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));

        System.out.println(minSalary);// Optional[{ id='1008', name='扎克伯格', age='35', salary='2500.32'}]

        // 9 集合便利操作
        // 内部迭代 forEach(Consumer C)
        // 方法1
        employees.stream().forEach(System.out::println); // 遍历操作
        // 方法2
        employees.forEach(System.out::println);

        /**
         * 2 归约
         */
        // 计算所有数组的合
        // reduce(T identity, BinaryOperator)——可以将流中元素反复结合起来，得到一个值。返回 T
        List<Integer> intArrays = Arrays.asList(1, 2, 4, 5, 6, 6, 9);
        Integer sum = intArrays.stream().reduce(0, Integer::sum);
        System.out.println(sum); // 33

        // 计算公司所有员工工资的总和
        // reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
        Stream<Double> employeesStream2 = employees.stream().map(Employee::getSalary);
        Optional<Double> sumSalary = employeesStream2.reduce(Double::sum);
        System.out.println(sumSalary); // Optional[48424.08]

        /**
         * 3 收集
         */
        // 查询工资大于8000的员工，返回一个List 或者 Set
        // collect(Collector c)——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
        // List
        List<Employee> employeesList = employees.stream().filter(e -> e.getSalary() > 8000)
                .collect(Collectors.toList());
        System.err.println(employeesList); // [{ id='1002', name='马云', age='12', salary='9876.12'}, { id='1006',
                                           // name='比尔盖茨', age='42', salary='9500.43'}]

        // Set
        Set<Employee> employeesSet = employees.stream().filter(e -> e.getSalary() > 8000).collect(Collectors.toSet());
        employeesSet.forEach(System.out::println);

        // { id='1002', name='马云', age='12', salary='9876.12'}
        // { id='1006', name='比尔盖茨', age='42', salary='9500.43'}

    }
}
