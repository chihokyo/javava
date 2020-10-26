package com.finalkeyword.exer;
/**
 * final: 最终的
 * 1 fianl 可以修饰 类 方法 变量
 * 2 用来修饰一个类 不能被继承
 *          String System StringBuffer 都是不能被继承的
 * 3 用来修饰一个方法 不可以在被重写 override
 *      比如 Object类中的getClass()
 * 【补充一下 native关键字的意思就是调用底层代码了】
 * 4 用来修饰一个变量（和属性不一样，属性属于变量一种）
 *      4-A 修饰属性 就是常量了。final + 变量 = 常量
 *          4-1 显示初始化，代码块初始化，构造器初始化
 *          4-2 多个对象属性值都一样，就在显示初始化，代码块初始化里面写
 *          4-3 多个对象属性，不一样的话，就用构造器来写
 *      4-B 修饰局部变量 （方法内or形参）
 *          4-1 形参时候 final修饰形参时候，表明形参是常量，一旦赋值只能使用 不能赋值
 * 5 static final 只能用来修饰属性or方法 
 *                修饰属性：全局常量 （接口里常用）
 *                修饰方法：使用比较少，不能重写不能继承
 * 
 */
public class FinalTest {
    final int WIDTH = 0;
    final int LEFT;
    final int RIGHT;
    // final int DOWN;
    // 代码块
    {
        LEFT = 1;
    }
    // 构造器
    public FinalTest() {
        RIGHT = 2;
    }

    // public void setDown(int down) {
    //     this.DOWN = down;
    //     NG 加载构造器的问题，这个生命周期的时候 DOWN根本就没有
    // }

    public FinalTest(int n) {
        RIGHT = n; // 必须要和无参构造器一起RIGHT被赋值
    }
    
    final int width = 10;
    // public void setWidth() {
    //     width = 20; // 不能在被赋值 The final field FinalTest.width cannot be assigned
    // }
    // public static void main(String[] args) {
    //     int num = 10;
    //     num += 5;
    // }

    public void show() {
        final int NUM = 10; //  局部变量常量就不能被赋值了
        // NUM += 5 NG
    }
    public void show2(final int NUM2) {
        NUM = 10; //  形参比较特别，必要才赋值
        //所以只能调用，无法修改
    }
}

// 到此为止了 太监类
final class FinalA {

}

// class FinalB extends FinalA{} NG 不能被其他类继承
// class FinalB extends String{} NG 实际上封装的是 char数组 不能轻易被改变
// class AA {
//     public final void name() {
        
//     }
// }

// class BB extends AA {
//     public void name() {
//         不能重写了
//     }
// }