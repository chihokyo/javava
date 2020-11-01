package com.threadexer.java;
/**
 * Thread类
 * 使用同步代码块解决线程安全问题
 * 锁 static 
 * 那么容易的解决
 * 
 * 在继承Thread类创建多线程的方式中，慎用this来充当同步监视器。一定要考虑参数唯一。
 */
public class TicketThreadExer2 {
    public static void main(String[] args) {

        TicketWindow2 t1 = new TicketWindow2();
        TicketWindow2 t2 = new TicketWindow2();
        TicketWindow2 t3 = new TicketWindow2();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketWindow2 extends Thread {

    private static int tickets = 100;
    // Thread继承类的实现多线程的方式。因为这个时候是多个对象实例，所以锁并不唯一。
    // 所以这个时候对象实例一定要是一个static的
    // private static Object o = new Object();

    public void run() {
        while (true) {
            // 不同于接口方式的多线程，这里的synchronized (o) 不能写成this，对象也不是唯一
            // 但是可以写  TicketWindow2.class 拿当前类可以充当
            // 因为类也是对象。反射的时候可以细节。比如简单理解一下
            // Class clazz = TicketWindow2.class
            // 类 类变量 是一个对象  TicketWindow2 可以说是一个对象 
            // 以后会深究 TicketWindow2.class 是唯一的 只会加载一次
            // synchronized (o) {
            synchronized (TicketWindow2.class) {
                if (tickets > 0) {
                    System.out.println(getName() + ":卖票， 票号是:" + tickets);
                    tickets--;
                } else {
                    break;
                }
            }
        }
    }
}