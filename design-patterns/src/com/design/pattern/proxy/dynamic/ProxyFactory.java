package com.design.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 被代理类
 */
public class ProxyFactory {

    // 被代理类 老师们？or 反正是动态的 只要是被代理的都可以写在这里
    private Object target;

    // 对被代理类进行初始化
    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 获取一个实例，这里出来的是一个代理类 中介机构
    public Object getProxyInstance() {
        // 这里要点开始！！！
        // 1 用了反射
        // 参数1 是类加载器
        // 参数2 是接口 必须有接口才知道彼此什么是相同的
        // 参数3 这里最难理解 是事件处理 可以触发什么方法 有什么参数 都在这里

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    // 这里必须要实现invoke 不然根本不知道触发什么事件
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理开始");
                        // 这里比较容易理解的就是平常都是对象-方法这样调用
                        // 这里用的是方法 直接触发了对象来调用 可以理解成翻转
                        Object returnVal = method.invoke(target, args);
                        System.out.println("动态代理结束");
                        return returnVal;
                    }
                });
    }
}
