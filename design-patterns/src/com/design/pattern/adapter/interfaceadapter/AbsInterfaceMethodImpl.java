package com.design.pattern.adapter.interfaceadapter;

/**
 * 抽象类实现接口
 */
public abstract class AbsInterfaceMethodImpl implements InterfaceMethod {

    // 先把接口InterfaceMethod的方法默认实现以下
    // 其实抽象类不一定要实现接口全部方法
    // 抽象类可以不用实现接口的全部方法

    // 有的时候需要将接口和抽象类配合起来使用，
    // 这样可以为开发者提供相当的便利性，开发者觉得哪个方便就选用哪个。
    // 这样的抽象类称为便利类。此时，便利类并不需要实现接口的所有方法，可以留给继承它的子类去实现它们。

    // 这么做并非是没有意义的，当你自己写的类想用接口中个别方法的时候（注意不是所有的方法），
    // 那么你就可以用一个抽象类先实现这个接口（方法体中为空），
    // 然后再用你的类继承这个抽象类，这样就可以达到你的目的了，
    // 如果你直接用类实现接口，那是所有方法都必须实现的。

    // 下面的方法默认都不实现的意义是防止后来不小心调用？
    @Override
    public void methodA() {
    }

    @Override
    public void methodB() {

    }

    @Override
    public void methodC() {

    }

    @Override
    public void methodD() {

    }
}
