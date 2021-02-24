package com.generic.exer;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于通配符的限制条件-super extends
 * 
 * ? extends A:
                G<? extends A> 可以作为G<A>和G<B>的父类，其中B是A的子类

    ? super A:
                G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类
 */
public class GenericExtendTest2 {
    public static void main(String[] args) {

        List< ? extends Person> list1 = null;
        List< ? super Person> list2 = null;

        List<Student> listStudent = new ArrayList<>();
        List<Person> listPerson = new ArrayList<>();
        List<Object> listObject = new ArrayList<>();
        
        // 1【赋值操作】
        // extends 相当于 小于等于 <= 意思就是说 比Person小或者等于Person的才可以 所以
        list1 = listStudent;
        list1 = listPerson;
        // list1 = listObject; NG 比Person大了

        // super 相当于 大于 意思就是说 >= Person 一定要比Person大的类才行
        // list2 = listStudent; NG 比Person小了
        list2 = listPerson;
        list2 = listObject;

        // 2【读取操作】
        list1 = listStudent; // 子类给到父类这样OK listStudent → Person
        Person p = list1.get(0); // 这里list1 本来就是Person泛型 OK的
        // Student s = list1.get(0); NG 因为lis1是Person 不可能赋值给比自己更小的

        list2 = listPerson;
        Object o =  list2.get(0); // 肯定可以的 毕竟super Person 就是要找一个比Person大的

        // Person p = list2.get(0); NG 一定要大于Person

        
        // 3【写入操作】
        // NG 如果范围小于Student怎么办，那就无法放进去了。（ 你要放的类 | Student |Person）
        // list1.add(new Student()); NG
        // 如果是大于了Person 就是不行的，小于可以是多态。
        list2.add(new Person());
        list2.add(new Student());


    }
}

class Person {

}

class Student extends Person {

}
