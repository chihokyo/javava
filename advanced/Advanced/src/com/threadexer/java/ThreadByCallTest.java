package com.threadexer.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * 创建线程的方式三：实现Callable接口。 --- JDK 5.0新增
 *
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大？
 * 1. call()可以有返回值的。
 * 2. call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3. Callable是支持泛型的
 
 */
public class ThreadByCallTest {
    public static void main(String[] args) {
        // 创建 Callable 接口实现类的对象
        NumberThread numberThread = new NumberThread();
        // 将这个Callable 接口实现类的对象作为参数穿度到 FutureTask 的构造器当中 创建 FutureTask对象
        // 强制类型转换
        FutureTask futureTask = new FutureTask(numberThread);
        // FutureTask对象作为参数传递到Thread类构造器中，创建 Thread对象
        new Thread(futureTask).start();

        // 获取
        try {
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

// 1.创建一个实现Callable的实现类
class NumberThread implements Callable {
    // 2.实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}