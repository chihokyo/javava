package com.jdbc1.exer;

import java.util.Date;

public class Order {
    private int orderId;
    private String orderName;
    private Date orderDate;


    public Order() {
    }

    public Order(int orderId, String orderName, Date orderDate) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return this.orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Order orderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public Order orderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public Order orderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            ", orderName='" + getOrderName() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            "}";
    }

}
