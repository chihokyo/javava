package com.project1.exer;
/**
 * 员工状态
 * FREE 空闲
 * VOCATION 度假中
 * BUSY 很忙
 */
public class Status {

    private final String NAME;
    private Status(String name) {
        this.NAME = name;
    }
    public static final Status FREE = new Status("FREE");
    public static final Status VOCATION = new Status("VOCATION");
    public static final Status BUSY = new Status("BUSY");

    @Override
    public String toString() {
        return NAME;
    }

}
