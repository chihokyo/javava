package com.design.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个实体类 
 * University → Colleague → Department 这个一个组织
 */
public class University extends OrganizationComponent {

    // 这里存放的其实是 Colleague
    List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    // 重写add
    @Override
    protected void add(OrganizationComponent o) {
        organizationComponents.add(o);
    }

    // 重写remove
    @Override
    protected void remove(OrganizationComponent o) {
        organizationComponents.remove(o);
    }  

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    // 输出 University
    @Override
    protected void print() {
        System.out.println("*****" + getName() + "*****" + getDesc());
        for (OrganizationComponent organizationComponent : organizationComponents) {
            // 因为每一个都有print方法 都必须实现 所以直接使用各个节点的print方法
            organizationComponent.print();
        }
    }
}
