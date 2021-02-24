package com.stringclass.com;
/**
 * 考察的值传递
 * 
 * String str = new String("good"); 变量
 * 这里的str 因为是一个 引用数值类型 所以给 地址也给了下面
 * public void change(String str,  char ch[]) { 这里一份
 * 但是由于str是不可变的。所以 str = "test ok";的时候 其实 这里 str 还是没有变的
 * 值传递 基本传递数据传递数据 引用数据类型是一个地址
 * 
 * char ch[] 也是地址 数组因为是可变的。因为地址一样，所以通过一个改的，对另一个也有影响
 */
public class StringInstanceTest2 {

    String str = new String("good");
    char[] ch = { 't', 'e', 's', 't' };

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {

        StringInstanceTest2 st = new StringInstanceTest2();
        st.change(st.str, st.ch);
        System.out.println(st.str); // good
        System.out.println(st.ch); // best 直接修改了地址里面的char[]数组
    }

}
