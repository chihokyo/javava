package com.project1.exer;
/**
 * PC 类 实现 Equipment接口
 */
public class PC implements Equipment {
    
    private String model; // 机器型号
    private String display; // 显示器名称

    public PC() {
        super();
    }

    public PC(String model, String display) {
        super();
        this.model = model;
        this.display = display;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String getDescription() {
        return model + "(" + display + ")";
    }

}
