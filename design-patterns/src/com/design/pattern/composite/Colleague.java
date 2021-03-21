package com.design.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个实体类 
 * University → Colleague → Department 这个一个组织
 * 这里的 Colleague 是 University的子节点
 */
public class Colleague extends OrganizationComponent {
    
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    @Override
    protected void add(OrganizationComponent o) {
        // 实际业务里 其实每一个add都不是一样的逻辑的
        organizationComponents.add(o);
    }

    @Override
    protected void remove(OrganizationComponent o) {
        organizationComponents.remove(o);
    }
    
    public Colleague(String name, String desc) {
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

    // 输出包含在 Colleague 所有学院
    @Override
    protected void print() {
        System.out.println("*****" + getName() + "*****");
        for (OrganizationComponent organizationComponent : organizationComponents) {
            // 因为每一个都有print方法 都必须实现 所以直接使用各个节点的print方法
            organizationComponent.print();
        }
    }
}
