package com.project1.exer;
/**
 * NoteBook 类 实现 Equipment接口
 */
public class NoteBook implements Equipment {

    private String model; // 型号
    private double price; // 价格

    public NoteBook() {
        super(); // 父类构造器，可写可不写
    }

    public NoteBook(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return model + "{" + price + "}";
    }

}
