package com.map.exer;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
/**
 * TreeMap添加一对 k和v 要求必须是同一类型
 * 
 * 自然排序
 * 定制排序
 */
public class TreeMapTest {
    public static void main(String[] args) {

        // 1 自然排序 就是按照 compareTo
        TreeMap<User, Integer> map = new TreeMap<User, Integer>();

        User u1 = new User("Tome", 23);
        User u2 = new User("Jerry", 30);
        User u3 = new User("Amy", 90);
        User u4 = new User("Amy", 88);
        User u5 = new User("Rose", 18);

        map.put(u1, 90);
        map.put(u2, 92);
        map.put(u3, 19);
        map.put(u4, 44);
        map.put(u5, 44);

        Set<Entry<User, Integer>> entrySet = map.entrySet();

        Iterator<Entry<User, Integer>> ite = entrySet.iterator();

        while (ite.hasNext()) {
            Entry<User, Integer> entry = ite.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("*************华丽的分割线**************");

        // 2 定制排序
        TreeMap<User, Integer> map2 = new TreeMap<User, Integer>(new Comparator<User>() {
            public int compare(User o1, User o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    return Integer.compare(u1.getAge(), u2.getAge());
                }

                throw new RuntimeException("输入的类型不一致");
            };
        });

        User uu1 = new User("Tome3", 23);
        User uu2 = new User("Jerry2", 30);
        User uu3 = new User("Amy1", 90);
        User uu4 = new User("Amy2", 88);
        User uu5 = new User("Rose4", 18);

        map2.put(uu1, 90);
        map2.put(uu2, 92);
        map2.put(uu3, 19);
        map2.put(uu4, 44);
        map2.put(uu5, 44);

        Set<Entry<User, Integer>> entrySet2 = map2.entrySet();
        Iterator<Entry<User, Integer>> ite2 = entrySet2.iterator();

        while (ite2.hasNext()) {
            // 写法1
            Entry<User, Integer> entry2 = ite2.next();
            User key = entry2.getKey();
            Integer value = map2.get(key);
            System.out.println(key + "----" + value);
            // 写法2
            // Entry<User, Integer> entry2 = ite2.next();
            // System.out.println(entry2.getKey() + "===" +entry2.getValue());
        }

    }
}

/**
 * 测试用User类
 */

class User implements Comparable {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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

    public User name(String name) {
        this.name = name;
        return this;
    }

    public User age(int age) {
        this.age = age;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name) && age == user.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", age='" + getAge() + "'" + "}";
    }

    @Override
    public int compareTo(Object o) {
        // 按照名字从大到小排列，年龄从小到大排列
        if (o instanceof User) {
            User user = (User) o;
            int compare = -this.name.compareTo(user.name);
            if (compare != 0) {
                return compare;
            } else {
                return Integer.compare(this.age, user.age);
            }
        } else {
            throw new RuntimeException("输入类型不匹配");
        }
    }

}