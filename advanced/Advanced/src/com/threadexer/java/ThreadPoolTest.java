package com.threadexer.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
/**
 * 创建线程的方式四：使用线程池
 *
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大线程数
 *      keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 *
 * 面试题：创建多线程有几种方式？四种！
 * 
 * Thread类
 * Runnable
 * Callable
 * ThreadPool
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 1 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 强制类型转换 才能使用线程池子的属性 上面的并非线程池属性
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

        // 设置线程池的属性
        // System.out.println(service.getClass());
        // service1.setCorePoolSize(10);
        // service1.setKeepAliveTime(time, unit);

        // 2 执行指定的线程池的操作，需要提供实现 runnable or callable 接口实现类的对象

        service.execute(new NumberThreadOdd());
        service.execute(new NumberThreadEven());

        // 3 关闭线程池
        service.shutdown();
    }
}

class NumberThreadOdd implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }

        }
    }
}

class NumberThreadEven implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}