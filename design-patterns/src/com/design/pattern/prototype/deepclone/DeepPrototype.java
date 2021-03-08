package com.design.pattern.prototype.deepclone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 原型模式
 */
public class DeepPrototype implements Serializable, Cloneable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String name; // 基本属性类型
    public DeepCloneableTarget deepCloneableTarget; // 引用数据类型
    // 构造器

    public DeepPrototype() {
        super();
    }

    /**
     * 深拷贝方式1 使用重写clone方法
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 1 新建一个对象
        Object deep = null;
        // 2 拷贝基本数据 & String
        deep = super.clone();
        // 3-1 对引用数据类型单独处理 先转换deep到这个类的类型，这样才能用类的属性
        DeepPrototype deepPrototype = (DeepPrototype) deep;
        // 3-2 然后拷贝这个类的属性（引用数据类型）记得要强转
        deepPrototype.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();
        return deepPrototype;
    }

    /**
     * 深拷贝方式2 序列化
     */
    public Object deepClone() {

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // 反序列化 其实就是读取和读出，变相的复制一份
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepPrototype copyObj = (DeepPrototype) ois.readObject();
            return copyObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                // 关闭流
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

}
