package com.main.exer;
/**
 * main()方法的使用说明
 * 1 main()方法程序的入口
 * 2 普通的静态方法 （静态方法里面只能调用静态）
 * 3 当前类 去调用 main static类调用staic方法
 * 4 void 返回值
 * 5 形参问题。String[] args 作为与控制台交互的方式 之前 （Scannar）
 */
public class MainTest {
    public static void main(String[] args) {
        Main.main(new String[100]);
        // show();NG
        MainTest m1 = new MainTest();
        m1.show();
    }

    public void show() {

    }
}

class Main {

    // 普通的静态方法
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = "arg_" + i;
            System.out.println(args[i]);
        }
    }
}