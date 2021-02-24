package com.design.princple;

public class InterfaceSegregationA {
    public static void main(String[] args) {

        SegregationAD ad = new SegregationAD();
        ad.depend1(new SegregationAB()); // SegregationAB seA1

        SegregationAE ae = new SegregationAE();
        ae.depend5(new SegregationAC()); // SegregationAC seA5
    }
}

interface SegregationA1 {
    void seA1();

    void seA2();

    void seA3();

    void seA4();

    void seA5();
}

class SegregationAB implements SegregationA1 {

    @Override
    public void seA1() {
        System.out.println("SegregationAB seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationAB seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationAB seA3");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationAB seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationAB seA5");
    }
}

class SegregationAC implements SegregationA1 {

    @Override
    public void seA1() {
        System.out.println("SegregationAC seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationAC seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationAC seA3");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationAC seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationAC seA5");
    }
}

// SegregationAD 通过接口 SegregationA1 去（依赖）使用 SegregationAB
// 但是只会用A1 A2 A3
// 这样的结果就是其实 SegregationAB 的 4和5白写了
class SegregationAD {
    public void depend1(SegregationA1 i) {
        i.seA1();
    }

    public void depend2(SegregationA1 i) {
        i.seA2();
    }

    public void depend3(SegregationA1 i) {
        i.seA3();
    }
}

// SegregationAE 通过接口 SegregationA1 去（依赖）使用 SegregationAC
// 但是只会用A1 A4 A5
// 这样的结果就是其实 SegregationAC 的 2，3白写了
class SegregationAE {
    public void depend1(SegregationA1 i) {
        i.seA1();
    }

    public void depend4(SegregationA1 i) {
        i.seA4();
    }

    public void depend5(SegregationA1 i) {
        i.seA5();
    }
}