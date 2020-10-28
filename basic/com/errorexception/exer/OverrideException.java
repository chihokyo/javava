package com.errorexception.exer;

import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 关于重写下的异常
 * 
 * 1 子类重写的方法抛出的异常不能大于父类
 * 2 如果父类没有异常，那么子类就一定没有异常
 * 3 除非子类用 trycatchfinally 自己写
 */
public class OverrideException {

    public static void main(String[] args) {
        OverrideException o = new OverrideException();
        o.display(new SubClass()); // 多态性
    }

    // 为什么子类重写的方法不能大于呢
    // 如果如上这个时候使用多态调用了SubClass
    // 发生了异常，这个时候子类的异常要大于父类的话，那么抛上去之后根本就抓不住
    // 因为已经算是抓住了一个大的异常，如果还有一个异常，那就没意义了
    // 如果父类没有异常，那么子类就一定没有异常
    public void display(SuperClass s) {
        try {
            s.superMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SuperClass {

    public void superMethod() throws IOException {

    }
}

class SubClass extends SuperClass {

    // 只能比父类小，所以这样NG
    // @Override
    // public void superMethod() throws Exception {
    // super.superMethod();
    // }

    @Override
    public void superMethod() throws FileNotFoundException {

    }
}