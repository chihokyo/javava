package com.innerclass.exer;

/**
 * 开发中常用的内部类的实现
 */
public class InnerClassTest1 {

    // 很少见 局部内部类
    public void innerMethod() {
        class AAA{
        }
    }


    // 基本上开发都是返回一个实现的接口对象
    // 比如这一个就是实现了 Comparable 接口类的对象

    public Comparable getComparable() {
        // 方式1 非匿名
        // class MyComparable implements Comparable {
        //     @Override
        //     public int compareTo(Object o) {
        //         return 0;
        //     }
        // }
        // return new MyComparable();


        // 方式2 匿名

        return new Comparable(){

			@Override
			public int compareTo(Object o) {
				return 0;
			}
			
		};
    }
}
