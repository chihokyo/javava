package com.design.pattern.prototype.deepclone;

public class Client {
    public static void main(String[] args) throws Exception {

        DeepPrototype p = new DeepPrototype();
        p.name = "yes";
        p.deepCloneableTarget = new DeepCloneableTarget("Bow", "small");

        System.out.println("测试方式1 clone方式");
        DeepPrototype p2 = (DeepPrototype) p.clone();

        System.out.println(p == p2); // false
        System.out.println(p.deepCloneableTarget == p2.deepCloneableTarget); // false
        System.out.println(p.hashCode());
        System.out.println(p2.hashCode());

        System.out.println("测试方式2 序列化方式");
        DeepPrototype p3 = (DeepPrototype) p.deepClone();

        System.out.println(p == p3); // false
        System.out.println(p.deepCloneableTarget == p3.deepCloneableTarget); // false
        System.out.println(p.deepCloneableTarget.hashCode()); // 1528902577
        System.out.println(p3.deepCloneableTarget.hashCode()); // 1096979270
        System.out.println(p.name.hashCode()); // 119527
        System.out.println(p3.name.hashCode()); // 119527

    }
}
