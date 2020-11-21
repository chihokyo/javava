package com.io.exer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象流的使用
 * 1 ObjectInputStream 和 ObjectOutputStream
 * 2 作用，用于存储和读取基本数据类型or对象的处理流。强就强在可以把java对象写入数据源，也可以取出来
 * 
 * 序列化机制
 *  对象序列化允许把内存中的对象转换成和平台无关的二进制流，所以就可以进行持久化，或者通过网络传输。
 *  就是一个对象可以变成一段二进制的数据进行持久化&传输
 * 
 * 关于序列化的接口要求
 * 1 必须满足序列化接口
 * 2 serialVersionUID 常量
 * 3 内部属性全部都是序列化的属性
 * 
 *  补充：ObjectOutputStream和ObjectInputStream不能序列化 static 和 transient 修饰的成员变量
 * 因为static 是类的属性 而序列化的是对象。
 * 如果想让一个属性不想被序列化，那么就要加上transient关键字当做标签就好了。
 */
public class ObjectInputOutputSteamTest {
    public static void main(String[] args) {

        serialized();
        reSerialized();

    }

    /**
     * 序列化操作 → 内存java对象保存
     */
    public static void serialized() {
        // 1. 新建对象
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            // 2. 写入数据
            oos.writeObject(new String("我这是一条测试java对象能否进行保存的语句"));
            oos.flush();

            oos.writeObject(new Person("TOM", 90));
            oos.flush();

            oos.writeObject(new Person("AMY", 100));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 3. 关闭
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 反序列化操作 → 磁盘中数据还原成为java对象
     */
    public static void reSerialized() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Object obj = ois.readObject();
            String str = (String) obj;
            System.out.println(str);

            // 一般一个dat文件都是一个对象类型，不会包含多个对象类型
            // 现在开始读取Person类对象的数据
            // 一个flush对应一个对象

            Person p = (Person) ois.readObject();
            Person p1 = (Person) ois.readObject();

            System.out.println(p); // { name='TOM', age='90'}
            System.out.println(p1); // { name='AMY', age='100'}

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
    }
}


// 测试类 Person
// 1. 如果要使用序列化 那么这个类必须满足序列化要求 实现以下接口之一
// Serializable 
// Externalizable
// 2. 当前列必须要提供一个全局常量： serialVersionUID
//      必须要有一个 serialVersionUID 序列番号 属性
// 关于这个 serialVersionUID 是一个序列化版本标识符的常量 显示声明可以保证序列化和反序列化一致性
// 如果是隐式的也就是系统给的。如果对象一旦改变，那么还原的时候可能发生改变。
// 3. 除了保证Person类接口之外，还必须保证内部的属性也必须是可序列化的
// 默认情况下 基本数据类型都是可持续化的， String 也是可以持续化的
// 如果其中一个变量不是序列化的 就会出错

class Person implements Serializable {

    private static final long serialVersionUID = -2356432702308176910L;
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
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

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public Person age(int age) {
        this.age = age;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(name, person.name) && age == person.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }
}