package com.finalkeyword.exer;
/**
 * final关键字的练习
 */
public class FinalTest2 {

    /*************练习1*****************/
    
    public int add(final int x) {
        // return ++x;  NG 编译就出错了，因为这包含x本身++之后又返回了
        return x + 1; // 这个可以 ∵只是返回
    }


    /***************练习2***************/

    public void addOne(final Other o) {
        // o = new Other(); NG 这不行了，因为已经重新把地址值给改变了
        o.i++; // 编译运行应该都没问题。o里面是一个地址值只要不重新赋值就可以，里面的变量变化也可以
    }
    public static void main(String[] args) {
        Other o = new Other();
        new FinalTest2().addOne(o);
    }
}


class Other {
    public int i;
}