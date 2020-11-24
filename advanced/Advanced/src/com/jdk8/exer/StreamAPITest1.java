package com.jdk8.exer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 1 Stream关注对于数据的运算，和CPU打交道。 Set集合关注的是数据的存储，和内存打交道。 
 * 2 Stream 自己不会存储元素 Stream
 * 不会改变源对象，相反，会返回一个带有结果的新Stream 
 * Stream 是延迟执行的，意味着他们会等到需要结果的时候财智星
 * 
 * 3 执行过程 
 *      1 实例化 2 一系列的中间操作（过滤，映射） 3 终止操作
 * 
 * 4 一个中间操作连，对数据源的数据进行处理。 一旦执行终止操作，就执行中间操作链并产生结果，然后就不会被使用
 * 
 */
public class StreamAPITest1 {
    public static void main(String[] args) {
        // 创建方式一 集合
        List<Employee> employees = EmployeeData.getEmployee();
        // 顺序流
        // default Stream<E> stream()
        Stream<Employee> stream = employees.stream();
        // 并行流
        // default Stream<E> parallelStream()
        Stream<Employee> paralleStream = employees.parallelStream();
        
        // 创建方式二 数组
        int[] arr = new int[]{1, 2, 3, 4, 5};
        IntStream is = Arrays.stream(arr);

        Employee e1 = new Employee(1000, "Tom");
        Employee e2 = new Employee(1001, "Jerry");
        Employee[] empArr = new Employee[]{e1, e2};
        Stream<Employee> stream1 = Arrays.stream(empArr);

        // 创建方式三 Stream的of
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4);

        // 创建方式四 创建无限流
        // 迭代
        // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        // 参数1种子 参数2，接口 UnaryOperator 继承于  public interface UnaryOperator<T> extends Function<T, T>
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        // 生成
        // public static<T> Stream<T> generate(Supplier<? extends T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);


        
    }
}
