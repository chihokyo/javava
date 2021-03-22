package com.design.pattern.proxy.cglib;

public class ClientMain {
    public static void main(String[] args) {
        
        Teacher target = new Teacher();
        Teacher proxyInstance = (Teacher)new ProxyFactory(target).getProxyInstance();

        String res = proxyInstance.teach();
        System.out.println(res);

        // cglib 开始
        // 老师真实教学 cglib代理模式
        // cglib 结束
        // cglib teach
    }
}
