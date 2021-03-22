package com.design.pattern.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib实现动态代理
 * 代理工厂
 */
public class ProxyFactory implements MethodInterceptor {

    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }
    public Object getProxyInstance() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
        System.out.println("cglib 开始");
        Object returnVal = method.invoke(target, args);
        System.out.println("cglib 结束");
        return returnVal;
    }
}
