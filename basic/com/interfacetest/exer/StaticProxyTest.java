package com.interfacetest.exer;
/**
 * 接口的应用：代理模式2
 * 模拟一个明星经纪人模式的接口代理应用
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        // RealStar star = new RealStar();
        // ProxyStar proxy = new ProxyStar(star);
        ProxyStar proxy = new ProxyStar(new RealStar());
        proxy.confer();
        proxy.signContract();
        proxy.bookTicket();
        proxy.sing();
        proxy.collectMoney();
    }
}

interface Star {

    void confer();

    void signContract();

    void bookTicket();

    void sing();

    void collectMoney();
}

class RealStar implements Star {
    public void confer() {
    };

    public void signContract() {
    };

    public void bookTicket() {
    };

    public void collectMoney() {
    };

    // 除了歌自己唱的以外。其他都是经纪人干
    @Override
    public void sing() {
        System.out.println("开麦真唱！");
    }
}

class ProxyStar implements Star {

    private Star real;

    public ProxyStar(Star real) {
        this.real = real;
    }

    @Override
    public void confer() {
        System.out.println("ProxyStar confer ");
    }

    @Override
    public void signContract() {
        System.out.println("ProxyStar signContract ");
    }

    @Override
    public void bookTicket() {
        System.out.println("ProxyStar bookTicket ");
    }

    @Override
    public void sing() {
        // 经纪人不唱，巨星唱
        real.sing();
    }

    @Override
    public void collectMoney() {
        System.out.println("ProxyStar collectMoney ");
    }

}