package com.design.pattern.proxy.staticproxy;

/**
 * 静态代理-被代理类 
 * 老师
 */
public class Teacher implements ITeacher {
    @Override
    public void teach() {
        System.out.println("老师亲自教学");
    }
}
