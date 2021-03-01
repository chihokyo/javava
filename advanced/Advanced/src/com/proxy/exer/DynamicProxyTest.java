package com.proxy.exer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的举例
 * 逻辑感人
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        // 测试
        // 传入一个被代理类的对象
        SuperHuman sh = new SuperHuman();
        // 这里就是代理类对象，通过代理类对象进行
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(sh);
        // 这里发现调用的是 SuperHuman
        System.out.println(proxyInstance.getBelief());  // SuperHuman getBelief()
        proxyInstance.eat("超女"); // 我喜欢吃东西呢: 超女

        System.out.println("****华丽的分割线****");

        NikeClothFactory ncf = new NikeClothFactory();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(ncf);
        proxyClothFactory.productCloth(); // Nike开始生产衣服了
        
    }
}

// 接口
interface Human {

    String getBelief();
    void eat(String food);

}

// 被代理类
class SuperHuman implements Human {
    
    @Override
    public String getBelief() {
        return "SuperHuman getBelief()";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃东西呢: " + food);
    }
}


/**
 * 实现动态代理需要解决的问题
 * 动态代理就是根据被代理类而创建代理类和实现方法，那么问题来了。
 * 问题1 如何根据加载到内存的被代理类，动态的创建一个代理类和对象
 * 问题2 当通过代理类的对象调用方法a是，如何动态的去调用被代理类的同名方法a
 */

class ProxyFactory {

    // 这里的 Object 就是被代理类的对象，通过此方法，就要返回一个代理类的对象 解决问题1
    // 也就是进入被代理类，出来代理类
    public static Object getProxyInstance(Object obj) {

        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);
        // getClass() → 返回运行时类
        // getClassLoader() → 返回类的加载器
        // 为什么getInterfaces() 要接口，因为被代理类和代理类都要实现同一个接口
        // 参数1 获取被代理类的类
        // 参数2 获取被代理的接口
        // 参数3 解决问题2
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    
    private Object obj; // 这里需要被代理类赋值

    // 使用这个方法就是给上面的obj赋值用的
    public void bind(Object obj) {
       this.obj = obj; 
    }

    // 当我们通过代理类的对象，调用方法a的时候，就需要自动调用如下方法
    // 上面的 newProxyInstance 参数3，需要的就是 MyInvocationHandler 类型的对象
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 参数1 代理类的对象
        // 参数2 代理类调用的方法
        // 参数3 形参列表
        
        // 调用被代理类对象
        Object returnVal = method.invoke(obj, args);
        return returnVal;
    }
}
