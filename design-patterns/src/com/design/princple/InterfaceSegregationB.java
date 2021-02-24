package com.design.princple;

public class InterfaceSegregationB {
    public static void main(String[] args) {

        BBA bba = new BBA();
        // BBA 通过 接口 去依赖 BA
        bba.depend1(new SegregationBA()); // SegregationBA seA1
        bba.depend2(new SegregationBA()); // SegregationBA seA2
        bba.depend3(new SegregationBA()); // SegregationBA seA3

        BBB bbb = new BBB();
        // BBB 通过 接口 去依赖 BB
        bbb.depend1(new SegregationBB()); // SegregationBB seA1
        bbb.depend4(new SegregationBB()); // SegregationBB seA4
        bbb.depend5(new SegregationBB()); // SegregationBB seA5
    }
}

// 根据具体需求把上面进化前的接口拆分成了3份
interface SegregationB1 {
    void seA1();
}

interface SegregationB2 {
    void seA2();

    void seA3();
}

interface SegregationB3 {
    void seA4();

    void seA5();
}

// 分别进行实现 需要什么实现什么
class SegregationBA implements SegregationB1, SegregationB2 {

    @Override
    public void seA1() {
        System.out.println("SegregationBA seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationBA seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationBA seA3");
    }

}

// 分别进行实现 需要什么实现什么
class SegregationBB implements SegregationB1, SegregationB3 {

    @Override
    public void seA1() {
        System.out.println("SegregationBB seA1");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationBB seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationBB seA5");
    }

}

class BBA {
    // 这里就是放进去需要实现的接口
    public void depend1(SegregationB1 i) {
        i.seA1();
    }

    public void depend2(SegregationB2 i) {
        i.seA2();
    }

    public void depend3(SegregationB2 i) {
        i.seA3();
    }
}

class BBB {
    public void depend1(SegregationB1 i) {
        i.seA1();
    }

    public void depend4(SegregationB3 i) {
        i.seA4();
    }

    public void depend5(SegregationB3 i) {
        i.seA5();
    }
}