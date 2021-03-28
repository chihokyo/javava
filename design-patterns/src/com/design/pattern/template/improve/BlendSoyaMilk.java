package com.design.pattern.template.improve;
/**
 * 纯豆浆
 * 模块方式 带钩子
 */
public class BlendSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        // 因为钩子方法表示不添加 所以这里并不需要实现
    }

    @Override
    public boolean isAddCondiments() {
        return false;
    }
}
