package com.design.pattern.proxy.cglib;
/**
 * cglib实现动态代理
 */
public class Teacher {
    public String teach() {
        System.out.println("老师真实教学 cglib代理模式");
        return "cglib teach";
    }
}
