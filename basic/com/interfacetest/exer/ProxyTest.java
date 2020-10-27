package com.interfacetest.exer;
/**
 * 接口的应用：代理模式
 * 这个模式主要就是。自己不做，但是让代理做。
 * 实例化的是【代理】
 * 真正执行的是【自己】
 * 这里用一个浏览器代理的小练习来感觉一下
 */
public class ProxyTest {
    public static void main(String[] args) {
        Server server = new Server();
        ProxyServer p = new ProxyServer(server);
        p.browse();
            // ProxyServer Checking...
            // Server browse
        // 如果不是被代理的模式，还需要直接写
        // server.browse();
    }
}

// 定义一个接口，用来定义规范
interface Network {
    public void browse();
}

// 被代理类，不直接参与
// 但是因为实现了Network接口，所以要实现所有方法
class Server implements Network {

    @Override
    public void browse() {
        System.out.println("Server browse");
    }
}

// 代理类
// 定义一个私有接口对象
// 构造器里要初始化 接口对象的属性
// 添加自己的方法 check
// 重写接口
class ProxyServer implements Network {

    private Network proxyWork;

    // 构造器对属性进行初始化
    public ProxyServer(Network proxyWork) {
        this.proxyWork = proxyWork;
    }

    // 代理类的 其他方法
    public void check() {
        System.out.println("ProxyServer Checking...");
    }

    @Override
    public void browse() {
        // 校验
        check();
        // 因为真正联网的不是代理类，还是被代理类
        proxyWork.browse();
    }
}

