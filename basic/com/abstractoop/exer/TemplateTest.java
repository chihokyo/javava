package com.abstractoop.exer;
/**
 * 抽象类的应用 
 * 模板方式的设计模式
 */
public class TemplateTest {
    public static void main(String[] args) {
        SubTemplate s = new SubTemplate();
        s.spendTime();
    }
}

abstract class Template {

    // 计算某段代码所需要的时间
    public void spendTime() {
        long start = System.currentTimeMillis();
        // 这里就写不同的部分，不确定的部分，需要测试的部分。
        this.code();
        long end = System.currentTimeMillis();

        System.out.println("spend time + " + (end - start));
    }

    public abstract void code();
}

class SubTemplate extends Template {

    @Override
    public void code() {
        for (int i = 2; i <= 1000; i++) {
            boolean isFlag = true;
            for (int j = 2; j < Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isFlag = false;
                    break;
                }
            }
            if (isFlag) {
                System.out.println(i);
            }
        }

    }
}
