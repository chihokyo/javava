package com.design.pattern.proxy.dynamic;
/**
 * 动态代理
 * 执行类
 */
public class ClientMain {
    public static void main(String[] args) {

        // 创建被代理类
        Teacher target = new Teacher();
        // 动态创建代理类 中介
        ITeacher proxyObject = (ITeacher)new ProxyFactory(target).getProxyInstance();
        // 直接使用被代理类调用-无参数
        proxyObject.teach();

        // 直接使用被代理类调用-带参数
        System.out.println(proxyObject.sayHello("Amy"));
        // 这里可以看出来一个问题
        // 就是返回值 其实是最后返回来的
        // 所以跟上面的方法结果不一样
        // 这里的结果是

        // 动态代理开始
        // 动态代理结束
        // SayHello to Amy
    }
}
