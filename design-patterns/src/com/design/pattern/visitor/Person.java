package com.design.pattern.visitor;
/**
 * 访问者模式
 */
public abstract class Person {
    // 提供一个方法可以让Action访问
    // 这里相当于和Action和Person互相进行关联了
    public abstract void accept(Action action);
}
