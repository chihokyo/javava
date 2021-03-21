package com.design.pattern.composite;
/**
 * 一个实体类 
 * University → Colleague → Department 这个一个组织
 * 这里的 Department 是 Colleague 的叶子 下面没有节点
 */
public class Department extends OrganizationComponent {
    // 因为这个数据叶子 也就是没有子节点了
    // 所以没有集合 也就没有add remove
    
    public Department(String name, String desc) {
        super(name, desc);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    @Override
    protected void print() {
        System.out.println(getName());
    }
}
