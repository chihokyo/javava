package com.generic.exer;

import java.util.List;

/**
 * 一个关于泛型方法和泛型接口的应用 模拟一个操作数据库表的操作 DAO → 表共通方法 DAOTEST → 实例化各种类（模拟表table）
 * 
 * Student     → 学生表 
 * StudenDAO   → 学生表操作 
 * Customer    → 用户表 
 * CustomerDAO → 用户表操作
 * 
 * class DAO<T>
 *  |- class StudenDAO extends DAO<Student> 继承之后实例化的 StudenDAO 对象就只能操作 Student
 *  |- class CustomerDAO extends DAO<Customer>
 * 
 */
public class ExerDAO {
    public static void main(String[] args) {
        // new一个只能操作 Customer 表的 实例
        CustomerDAO cDao = new CustomerDAO();
        // 因为这里是继承了 DAO 类 可以使用add方法
        cDao.add(new Customer()); 
        // 这里虽然返回的是 List<T> 但是由于使用了泛型，这里实例化就确定了类型是 Customer
        List<Customer> cList = cDao.getForList(10);
        System.out.println(cList);
    }
}


class DAO<T> {

    // 添加一条记录
    public void add(T t) {
        
    }
    // 删除一条记录
    public boolean remove(int index) {
        return false;
    }
    // 更新一条记录
    public void update(int index, T t) {
        
    }
    // 查询一条数据 获取类型是T
    public T getIndex(int index) {
        return null;
    }
    // 查询多条记录 是一个T类型的List
    public List<T> getForList(int index) {
        return null;
    }
    // 泛型方法
    // 举例 获取一条表有多少记录 → 这样就是 long类型
    // 获取最大的员工入职时间 → 这样就是一个 Date
    public <E> E getValue() {
        return null;
    }
}


class Student {

}

class Customer {

}

class StudenDAO extends DAO<Student> {
    // 只能操作 Student 表数据
}

class CustomerDAO extends DAO<Customer> {
    // 只能操作 Customer 表数据
}