package com.jdk9.exer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
/**
 * JDK9新特性
 *  6 Stream加强
 *  7 Optional新方法stream()
 * 
 */
public class JDKTest3 {
    public static void main(String[] args) {

        // 1 Stream加强
        List<Integer> list = Arrays.asList(22, 21, 18, 9, 10, 7, 99);
        // takeWhile 返回从头开始按照制定规则尽量多的元素
        list.stream().takeWhile(x -> x < 60).forEach(System.out::println);
        System.out.println("*************");
        // dropWhile 和上面相反，返回剩余的元素
        list.stream().dropWhile(x -> x < 60).forEach(System.out::println);

        System.out.println("*************");

        // of参数中多个元素，可以包含null
        Stream<Integer> s1 = Stream.of(1, 2, 3, null);
        s1.forEach(System.out::println);

        // of不能单独存储单个null值
        // Stream<Integer> s2 = Stream.of(null);

        // ofNullable形参变量可以为null值的单个元素
        Integer i = 10;
        i = null;
        Stream<Integer> s3 = Stream.ofNullable(i);
        long count = s3.count();
        System.out.println(count); // 0

        // 新增重载的方法

        // 输出从0~9的值
        // Stream.iterate(0,x -> x + 1).limit(10).forEach(System.out::println);
        // 输出从0~99的值
        // Stream.iterate(0, x -> x < 100, x -> x + 1).forEach(System.out::println);

        /******************************************************* */

        // Optional提供了新方法 Stream
        List<String> list2 = new ArrayList<>();
        list2.add("Tom");
        list2.add("Tom1");
        list2.add("Tom2");
        Optional<List<String>> op1 = Optional.ofNullable(list2);
        Stream<List<String>> ss1 = op1.stream();
        ss1.flatMap(x -> x.stream()).forEach(System.out::println);

    }
}
