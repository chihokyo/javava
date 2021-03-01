package com.threadexer.java;
/**
 * 简易买票系统（非完全瑕疵版本） 使用 Runnable 接口实现
 * 
 * 1 天然共享数据
 * 因为 TicketRunnable tRun = new TicketRunnable(); 本质上只创建了一个对象
 * 如果又增加了 TicketRunnable tRun2 = new TicketRunnable(); 那就没办法了。还是多个。
 * 
 * 2 出现的问题
 *      a 卖票出现了重票，错票。 线程安全问题
 *      b 当一个线程操作车票时候，尚未完成操作，其他线程参与进来，也操作车票
 *      c 如何解决，加锁。当a线程操作的时候，其他线程不能操作，直到操作完。即使线程a出现了阻塞。
 * 3 如何解决问题
 * 通过同步机制来解决。
 * 方式1 同步代码块
 *          synchronized(同步监视器){需要同步的代码} 操作共享数据的代码，即需要被同步的代码。共享数据在的位置。
 *          不同于 try ，这里的需要同步的代码一定不能多，不能少。         
 *          同步监视器是什么，俗称，锁。任何一个类的对象都可以使是锁。
 *          要求 多个线程必须公用同一个锁。
 * 方式2 同步方法
 * 
 * 4 同步的方式 
 *      好处：解决了线程安全问题 缺点：因为操作同步代码的手，只有一个线程参与，其他线程等待。相当于是一个单线程的过程。
 * 
 * 5 解决每一次都要new一个Object锁的问题
 * 
 *  在Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
 * 
 */
public class TicketThreadByRunExer {
    public static void main(String[] args) {

        TicketRunnable tRun = new TicketRunnable();

        Thread t1 = new Thread(tRun);
        t1.setName("窗口1");
        t1.start();

        Thread t2 = new Thread(tRun);
        t2.setName("窗口2");
        t2.start();

        Thread t3 = new Thread(tRun);
        t3.setName("窗口3");
        t3.start();
    }
}

class TicketRunnable implements Runnable {

    private int tickets = 100;
    // 新建一个对象，作为同步监视器，也就是一个锁。
    // 这个对象只能放在共通的地方，如果放在了下面的方法，那么就不是共用同一个锁了
    Object obj = new Object();
    Object dog = new Dog(); // 只要是一个对象。

    @Override
    public void run() {
        // Object obj = new Object(); NG 不能放在这里
        while (true) {
            // 下面就是需要被共享数据的代码
            // 方式1
            // 使用this 那么这个this是谁，其实就是调用run的对象。那就是↑
            // TicketRunnable tRun = new TicketRunnable(); 的tRun
            // 方式2
            // 新建一个唯一的对象
            synchronized (this) {
                if (tickets > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":卖票，票号是:" + tickets);
                    tickets--;
                } else {
                    break;
                }
            }
        }
    }
}

class Dog {
}