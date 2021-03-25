package com.design.pattern.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据结构
 * 管理了所有的人和增删改查
 */
public class ObjectStrucure {

    private List<Person> persons = new LinkedList<>();

    // 增加人到list
    public void attach(Person p) {
        persons.add(p);
    }

    // 移除
    public void detach(Person p) {
        persons.remove(p);
    }

    /**
     * 显示测评情况
     * 传入的是具体的Action
     * @param action 具体测评情况
     */
    public void display(Action action) {
        for (Person person : persons) {
            person.accept(action);
        }
    }
    
}
