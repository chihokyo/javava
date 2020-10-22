package com.polymorphism.exer;

public class PolyExer2 {
    public static void main(String[] args) {
        PolyExer2 p = new PolyExer2();

        // p.method(new PolyStudent());
        p.method(new PolyGradute());

    }

    public void method(PolyPerson e) {

        // 虚拟方法调用
        String info = e.getInfo();
        System.out.println(info);

        // 方式1
        if (e instanceof PolyGradute) {
            System.out.println("PolyPerson");
            System.out.println("PolyStudent");
            System.out.println("PolyGradute");
        } else if (e instanceof PolyStudent) {
            System.out.println("PolyPerson");
            System.out.println("PolyStudent");
        } else {
            System.out.println("PolyPerson");
        }

        // 方式2
        if (e instanceof PolyGradute) {
            System.out.println("PolyGradute");
        }
        if (e instanceof PolyStudent) {
            System.out.println("PolyStudent");
        }
        if (e instanceof PolyPerson) {
            System.out.println("PolyPerson");
        }

    }
}

class PolyPerson {

    protected String name = "PolyPerson";
    protected int age = 50;

    public String getInfo() {
        return "Name" + name + "¥n" + "Age" + age;
    }
}

class PolyStudent extends PolyPerson {

    protected String school = "PKU";

    public String getInfo() {
        return "Name" + name + "¥nAge" + age + "¥nSchool" + school;
    }
}

class PolyGradute extends PolyStudent {

    protected String major = "IT";

    public String getInfo() {
        return "Name" + name + "¥nAge" + age + "¥nSchool" + school + "¥nMajor" + major;
    }
}