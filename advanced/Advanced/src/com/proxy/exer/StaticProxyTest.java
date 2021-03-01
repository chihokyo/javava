package com.proxy.exer;
/**
 *  静态代理
 *  特点 代理类和被代理类在编译期间已经被确定
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        // 创建被代理类对象 耐克工厂
        // 左边是接口 右边是耐克工厂实例
        ClothFactory nike = new NikeClothFactory();
        // 创建代理类对象 外包血汗工厂 注意这里的参数，是把实例当做实参给了血汗工厂处理
        // 【这里是最重要的一步吧】 就是 虽然 nike的实例并没有进行生产衣服 nike.productCloth();
        // 但是最后得出的结果还是Nike进行生产，这就是代理的意义。
        ClothFactory proxyFactory = new ProxyClothFactory(nike);
        proxyFactory.productCloth();

    }
}

// 一个接口 表示主题
interface ClothFactory {
    // 一个服装工厂，主要用来生产衣服
    void productCloth();
}

/**
 * 代理类 → 外包公司
 */
class ProxyClothFactory implements ClothFactory {

    // 用被代理对象进行实例化 → 这里也需要理解
    // 结合下面的构造器，就是在上面main函数里进行 new ProxyClothFactory 的时候
    // 这个时候传入进去的不是接口，而是耐克这个工厂的对象进行实例化
    // 这就是造成的代理的假象
    private ClothFactory factory;

    // 构造器 ↓ 这里传入的不是接口哦，而是被代理类的实例 不然怎么调用下面的方法呢
    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    // 重写接口的生产衣服方法
    @Override
    public void productCloth() {
        System.out.println("****代理工厂开始工作****");
        // 注意，谁的实例谁执行 factory 是一个形象代言人
        factory.productCloth();
        System.out.println("****代理工厂结束工作！****");
    }
}

/**
 * 被代理类 → 耐克工厂
 */
class NikeClothFactory implements ClothFactory {

    @Override
    public void productCloth() {
        System.out.println("Nike开始生产衣服了");
    }
}
