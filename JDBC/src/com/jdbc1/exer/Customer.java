package com.jdbc1.exer;

import java.util.Date;

/**
 * ORM变成思想，（Object Relational Mapping）
 * 
 * 一个数据表对应一个java类 表中一条记录对应一个对象 表中一个字段对应一条属性
 * 
 */
public class Customer {

    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customer() {
    }

    public Customer(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Customer id(int id) {
        this.id = id;
        return this;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public Customer birth(Date birth) {
        this.birth = birth;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", birth='" + getBirth() + "'" +
            "}";
    }

}
