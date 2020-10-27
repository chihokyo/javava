package com.interfacetest.exer;
/**
 * 接口的使用
 * 
 * 1 接口的多态性
 * 2 接口，实际上也是一种规范
 * 3 开发中，体会面向接口编程
 * 【1】多态性的表现在，虽然
 * class Computer {
    public void transferData(USB usb) {
       usb.start();
       System.out.println("transfer detail"); 
       usb.stop();
    }
} 
这里写的是 transferData(USB usb) 输入的USB对象
但实际上这里的放进去的是 USB接口的实现类 

【2】如何实现规范呢
那就是只要是想实现电脑传输的功能，都需要去实现接口USB这个规范
interface USB {
    public abstract void start();
    void stop();
}

 */
public class USBTest {
    public static void main(String[] args) {

        Computer compu = new Computer();

        // 1 创建了接口的非匿名实现类的非匿名对象
        Flash flash = new Flash();
        compu.transferData(flash);

        // 2 非匿名类 匿名对象
        compu.transferData(new Printer());

        // 3 匿名类 非匿名对象
        USB phone = new USB() {
            @Override
            public void start() {
                System.out.println("Phone start");
            }

            @Override
            public void stop() {
                System.out.println("Phone stop");
            }
        };
        compu.transferData(phone);

        // 4 匿名类 匿名对象
        compu.transferData(new USB() {
            @Override
            public void start() {
                System.out.println("MP3 start");
            }

            @Override
            public void stop() {
                System.out.println("MP3 stop");
            }
        });
    }
}

class Computer {
    public void transferData(USB usb) {
        usb.start();
        System.out.println("transfer detail");
        usb.stop();
    }
}

interface USB {
    public abstract void start();

    void stop();
}

class Flash implements USB {
    @Override
    public void start() {
        System.out.println("Flash Start");
    }

    @Override
    public void stop() {
        System.out.println("Flash stop");
    }
}

class Printer implements USB {
    @Override
    public void start() {
        System.out.println("Printer Start");
    }

    @Override
    public void stop() {
        System.out.println("Printer stop");
    }
}