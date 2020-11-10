package com.generic.exer;

import java.util.ArrayList;
/**
 * 关于泛型类/接口的注意点
 * 
 *  1. 泛型可以有多个参数，用，进行连接。<K,V,T>
 *  2. 泛型构造器中间不能代参数 public GenericClass(){} → 错误的public GenericClass<E>(){}
 *  3. 实例化以后。类型必须要保持一致。
 *  4. 泛型不同 不能 进行相互赋值 ↓示例
 *  5. 泛型如果不进行指定，将会被擦除。默认就是成为了Object 但不等价
 *  6. 泛型如果是接口or类 则不能创建泛型类对象
 *  7. 简化操作。ArrayList<String> list1 = new ArrayList<String>(); 
 *        简化成 ArrayList<String> list1 = new ArrayList<>(); 
 *  8. 泛型里面不能指定【基本数据类型】，但可以指定包装类 比如 NO int YES Integer ↓示例
 *  9. 异常不能使用泛型
 *  10. 异常不能使用泛型
 *  11. 不能使用new[] E[] elements = (E[]) new Object(capacity) 但是可以进行强转 ↓示例
 *  12. 关于子父类继承的一堆情况
 */
public class OrderGenericTest2 {
    public static void main(String[] args) {
        // 4 泛型不同 不能 进行相互赋值
        // ArrayList<String> list1 = new ArrayList<>();
        // ArrayList<Integer> list2 = new ArrayList<>();
        // list1 = list2; NG
    }

    // 8 错误的，静态方法不能使用泛型。
    // 自定义泛型在实例化时才能确定类型
    // 静态方法在实例化之前就载入JVM当然就会出错。
    // public static void name(ArrayList<T> o) {
    //     System.out.println(o);
    // }


    // 11 不能使用new[] 但是可以进行强转 ↓示例
    // 因为 在new的时候才能确定对象的属性，但是泛型是有在实例化之后才能确定
    // 这个时候new根本是无法确定泛型类型的

    // T[] arr = new T[10]; NG
    // T[] arr = (T[]) new Object(10); YES
}
class Father<T1, T2> {
}

// 1 子类不保留 → 擦除 没有类型
class Son1 extends Father {
}

// 2 具体类型
class Son2 extends Father<Integer, String> {
}

// 3 子类保留父类全部泛型
class Son3<T1, T2> extends Father<T1, T2> {
}

// 4子类部分保留
class Son4<T2> extends Father<Integer, T2> {
}

// 5子类自定义 不保留
class Son5<A, B> extends Father {
}

// 6子类自定义 保留
class Son6<A, B> extends Father<Integer, String> {
}

// 7子类自定义 全部保留
class Son7<A, B, T1, T2> extends Father<T1, T2> {
}

// 8子类自定义 部分保留
class Son8<T2, A, B> extends Father<Integer, T2> {
}
