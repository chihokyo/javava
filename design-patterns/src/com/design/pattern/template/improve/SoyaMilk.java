package com.design.pattern.template.improve;

/**
 * 抽象类 
 * 表示豆浆
 */
public abstract class SoyaMilk {

    // 固定的模板 做成final是为了让子类继承的时候不被重写
    final void make() {
        select();

        // 钩子方法
        if (isAddCondiments()) {
            addCondiments();
        }
        
        soak();
        beat();
    }

    // 1 选材料
    void select() {
        System.out.println("1 SoyaMilk select");
    }

    // 2 添加材料 需要每个子类具体进行实现 所以是抽象方法
    abstract void addCondiments();

    // 3 开始浸泡
    void soak() {
        System.out.println("3 SoyaMilk soak");
    }

    // 4 打碎
    void beat() {
        System.out.println("4 SoyaMilk beat");
    }
    
    // 钩子方法，决定是否添加配料
    // 钩子方法不做任何事情 只是用来控制逻辑
    // 子类可以选择是否继承 
    public boolean isAddCondiments() {
        return true;
    }

}

