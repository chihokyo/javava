package com.generic.exer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 泛型的使用
 * 1 jdk 5.0新增的特性
 * 2 是类和接口的泛型 指定返回值的类型
 * 3 int 这种基础数据类型不能作为泛型的数据类型 必须是这些的包装类
 * 4 在集合中使用泛型
 * 
 * 泛型结构包括 1 泛型类 2 泛型接口 3 泛型方法
 * 
 *      其实泛型类和泛型接口几乎是一样的，接口也就是一个不能被实例化并且支持多重实现抽象的类呗。
 * 
 */
public class GenericTest {
    public static void main(String[] args) {

        /**************不适用泛型，会产生一些问题*************** */
        // 需求存放学生的成绩
        // 问题1 类型不安全
        // ArrayList list = new ArrayList();
        // list.add(18);
        // list.add(28);
        // list.add(90);
        // list.add(87);
        // list.add("Tom");
        // for (Object score : list) {
        //     // 问题2 强行转换时，产生类型转换异常 java.lang.ClassCastException:
        //     int stuScore = (Integer)score;
        //     System.out.println(stuScore);
        // }
        

        /**************使用泛型的情况 ArrayList *************** */
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(88);
        list.add(99);
        list.add(100);
        // 方式一 for 进行遍历
        // list.add("Tom") NG 编译就会进行类型检查。保证数据安全。
        // for (Integer score : list) {
        //     // 避免强转操作 就不会出现类型转换异常问题
        //     int stuScore = score;
        //     System.out.println(stuScore);
        // }

        // 方式二 Iterator进行遍历
        // 注意点 就是 这里返回的类型也是一个泛型，因为
        // // public Iterator<E> iterator() {
        // //     return new Itr();
        // // }
        // 源码进行定义的时候返回值就是指定的泛型
        // Iterator<Integer> ite = list.iterator();
        // while (ite.hasNext()){
        //     System.out.println(ite.next());
        // }

         /**************使用泛型的情况 HashMap *************** */
        // 源码就是一个键值对
        // public interface Map<K, V> {
        Map<String, Integer> map =  new HashMap<String, Integer>();

        map.put("Tom", 100);
        map.put("Amy", 3);
        map.put("Jack", 70);
        // 泛型嵌套！
        // 源码这里返回一个 然后 Set 返回的是 Entry，而 Entry 本身是一个泛型
        // Set<Map.Entry<K, V>> entrySet();
        Set<Entry<String, Integer>> entry = map.entrySet();
        // 如果这里不加 import java.util.Map.Entry;
        // 就要写成
        // Set<Map.Entry<String, Integer>> entry = map.entrySet();
        Iterator<Entry<String, Integer>> ite = entry.iterator();
        while (ite.hasNext()) {
            Entry<String, Integer> e = ite.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + " ===>>> " + value);
        }
    }
}
