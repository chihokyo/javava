package com.generic.exer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义泛型练习
 * 
 * 
 * 定义个泛型类 DAO2<T>，在其中定义一个Map 成员变量，Map 的键为 String 类型，值为 T 类型。
 * 
 * 分别创建以下方法： 
 * public void save(String id,T entity)： 保存 T 类型的对象到 Map 成员变量中 
 * public T get(String id)：从 map 中获取 id 对应的对象 
 * public void update(String id,T entity)：替换 map 中key为id的内容,改为 entity 对象 
 * public List<T> list()：返回 map 中存放的所有 T 对象 
 * public void delete(String id)：删除指定 id 对象
 * 
 */
public class GenericExer {
    public static void main(String[] args) {

        DAO2<UserDAO2> dao = new DAO2<>();
        dao.save("1001", new UserDAO2(01, 34, "Amy"));
        dao.save("1002", new UserDAO2(01, 19, "Tom"));
        dao.save("1003", new UserDAO2(01, 80, "Jerry"));
        dao.save("1004", new UserDAO2(01, 80, "yes"));

        dao.update("1003", new UserDAO2(01, 3, "Jerry2"));
        // 遍历输出方法1
        List<UserDAO2> result = dao.list();
        // System.out.println(result);
        // 遍历输出方法2
        result.forEach(System.out::println);

    }
}

class DAO2<T> {

    private Map<String, T> map = new HashMap<>();

    // 保存 T 类型对象到 Map 成员变量里
    public void save(String id, T entity) {
        map.put(id, entity);
    }

    // 通过 id 获取Map 中对应的对象
    public T get(String id) {
        return map.get(id);
    }

    public void update(String id, T entity) {
        if (map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    public List<T> list() {
        // NG 原因：从上直接强转到下的对象是不行的 类型不一致
        // Collection<T> values = map.values();
        // return (List<T>) values;

        // OK
        ArrayList<T> list = new ArrayList<>();
        Collection<T> values = map.values();
        for (T t : values) {
            list.add(t);
        }
        return list;
    }

    public void delete(String id) {
        if (map.containsKey(id)) {
            map.remove(id);
        }
    }

}

class UserDAO2 {
    private String name;
    private int age;
    private int id;

    public UserDAO2() {
    }

    public UserDAO2(int id, int age, String name) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDAO2 name(String name) {
        this.name = name;
        return this;
    }

    public UserDAO2 age(int age) {
        this.age = age;
        return this;
    }

    public UserDAO2 id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDAO2)) {
            return false;
        }
        UserDAO2 userDAO2 = (UserDAO2) o;
        return Objects.equals(name, userDAO2.name) && age == userDAO2.age && id == userDAO2.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, id);
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", age='" + getAge() + "'" + ", id='" + getId() + "'" + "}";
    }

}