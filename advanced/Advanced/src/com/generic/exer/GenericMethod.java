package com.generic.exer;

import java.util.ArrayList;
import java.util.List;
/**
 * 泛型结构之泛型方法
 * 
 * 1 泛型接口跟泛型方法没有关系。意思就是虽然泛型方法所属的接口即使不是泛型。方法本身也可以是泛型的 2
 * 泛型方法跟带不带泛型没有关系。方法中使用了泛型并不以为是就是泛型方法。反省参数与类的反省参数没有任何关系 public void setOrderT(T
 * orderT){} 这样不是的 3 泛型方法所属的类是不是泛型类都没有关系
 */
public class GenericMethod {
    public static void main(String[] args) {
        // 测试一下泛型方法
        // 1 新建一个Integer类型数组
        Integer[] intArray = new Integer[]{1,2,3,4,5};
        // 2 转换
        // 这里的泛型 Integer 是由于放进去的数组形式决定的
        // 泛型方法在调用时候，指名泛型参数的类型
        List<Integer> list = GenericMethod.copyFromArrayToList(intArray);
        // 3 输出 顺便测试一下List方法
        list.add(89);
        System.out.println(list);
    } 


    /**
     * 示例什么是泛型方法
     * 方法简介：把任意数据类型的数组换换成List
     * 1 public List<E> copyFromArrayToList(E[] arr) {
     * 如果按照上面的写，编译器就会认为E不是一个泛型，而是一个对象。普通的E类型的对象
     * 为了让编译器知道这是一个泛型，就需要在方法前 以后添加一个<E> 用以区分表示E并不是一个普通类，而是泛型
     * 2 泛型方法：可以使静态的。原因：泛型方法在调用时候方法本身是确定的，并非在实例化的时候确定。
     *  我个人理解的意思下面这种不行，是因为 ArrayList<T> 这是类的泛型 而泛型方法的泛型只是一个参数。
     *  public static void name(ArrayList<T> o) {
         System.out.println(o);
         }
     */
    public static <E> List<E> copyFromArrayToList(E[] arr) {

        ArrayList<E> list = new ArrayList<>();
        for (E e : arr) {
            list.add(e);
        }
        return list;
    }
}
