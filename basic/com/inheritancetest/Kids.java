package com.inheritancetest;

/**
 * 满足javabean的要求
 *  1 类是公共的 public
 *  2 空参构造器 并且权限也是 public
 *  3 必须有相关属性，如果属性是 private 但就必须提供 get set方法
 */

public class Kids extends ManKind {

    // 属性
    private int yearOld;

    // 构造器
    public Kids() {
    }

    public Kids(int yearOld) {
        this.yearOld = yearOld;
    }

    // 方法
    public void printAge() {
        System.out.println(yearOld);
    }

    public int getSex() {
        return yearOld;
    }

    public void setYearOld(int yearOld) {
        this.yearOld = yearOld;
    }
}
