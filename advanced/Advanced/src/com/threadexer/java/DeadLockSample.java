package com.threadexer.java;
/**
 * 演示什么是死锁。
 * 1 死锁就是不同的线程分别占用对方需要的同步资源不放手
 * 这样就会造成那种 A等B行动才能动 而 B等A行动才能动 这样的情况就是死锁。
 * 2 说明
 *  a 出现死锁后，不会出现异常，但所有的线程处于阻塞的状态，无法进行
 *  b 我们使用同步的时候，要避免出现死锁
 * 下面就是一个死锁的演示
 */
public class DeadLockSample {
    public static void main(String[] args) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        // 虽然不是死锁知识，补充一下。这里实现多线程的方式用的是匿名类的形式
        // 通过new Thread()在里面实现了一个匿名对象
        // 匿名线程
        new Thread(){
            public void run() {
                // 同步代码块
                synchronized(sb1){
                    sb1.append("a");
                    sb2.append("1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 同步代码块 sb1 需要 sb2
                    synchronized(sb2){
                        sb1.append("b");
                        sb2.append("2");
                        System.out.println(sb1);
                        System.out.println(sb2);
                    }
                }
            };
        }.start();

        // new Thread(new Runnable(){ 实现了 new Runnable 接口的对象
        new Thread(new Runnable(){
            @Override
            public void run() {
                synchronized(sb2){
                    sb1.append("c");
                    sb2.append("3");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // sb2 需要 sb1
                synchronized(sb1){
                    sb1.append("b");
                    sb2.append("2");
                    System.out.println(sb1);
                    System.out.println(sb2);
                }
            }
        }).start();

    }    
}
