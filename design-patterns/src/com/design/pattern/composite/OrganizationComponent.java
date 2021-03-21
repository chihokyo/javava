package com.design.pattern.composite;

/**
 * 一个抽象类 OrganizationComponent 
 * 用来把下面的所有节点的方法进行默认实现 也可以用接口
 */
public abstract class OrganizationComponent {

    private String name; // 名字
    private String desc; // 描述信息

    // 这个方法并非所有的子类（节点）都要实现 所以做成了普通方法
    // 默认实现
    protected void add(OrganizationComponent o) {
        throw new UnsupportedOperationException();
    }
    // 默认实现
    // 这个方法并非所有的子类（节点）都要实现 所以做成了普通方法
    protected void remove(OrganizationComponent o) {
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // 每一个子类（节点）都需要实现 所以做成了抽象方法
    protected abstract void print();

}
