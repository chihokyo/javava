package com.jdk8.exer;
/**
* 总共2个接口 1个父类
* 三个相同的方法
* 1 class Man implements Filial, Spoony
*      ↑ 子类 必须重写
* 2 class Man extends Father implements Filial, Spoony
*      ↑ 父类优先，接口必须要明示 Filial(接口名).super.help();
*/

public class CompareTest {
    public static void main(String[] args) {
        Man m1 = new Man();
        Man m2 = new Man();
        m1.help();
        m2.help();

        // WHO?
        // 老妈，我来救你了
        // 媳妇，别怕，我来了
        // WHO?
        // 老妈，我来救你了
        // 媳妇，别怕，我来了
    }
}

// 接口1
interface Filial {// 孝顺的
	default void help() {
		System.out.println("老妈，我来救你了");
	}
}
// 接口2
interface Spoony {// 痴情的
	default void help() {
		System.out.println("媳妇，别怕，我来了");
	}
}

class Father{
	public void help(){
		System.out.println("儿子，救我媳妇！");
	}
}

class Man extends Father implements Filial, Spoony {
    @Override
    public void help() {
        System.out.println("WHO?");
        super.help();
        Filial.super.help();
        Spoony .super.help();
    }
}
