package com.objectarray.exer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
/**
 * 常用方法3
 * 
 * 1.hashCode():返回当前对象的哈希值
 * 2. 集合 ---> 数组：toArray()
 * 3. 数组 到 集合（ArrayList） 注意 Integer 和 Int
 */
public class CollectionTest3 {
    public static void main(String[] args) {

        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        // 1.hashCode():返回当前对象的哈希值
        System.out.println(coll.hashCode()); // -1200490100

        // 2. 集合 ---> 数组：toArray()
        // coll 是集合 ArrayList 转换成对象数组
        Object[] arr = coll.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // 3. 数组 到 集合（ArrayList）
        // 新建一个数组
        // String[] arrStr = new String[]{"Tome, "jerry"};
        // 调用Arrays类的静态方法asList()
        String[] strArray = { "AA", "BB", "CC" };
        System.out.println(strArray);

        List<String> list1 = Arrays.asList(strArray);
        List<String> list2 = Arrays.asList(new String[] { "AAA", "BBB" });

        System.out.println(list1); // [AA, BB, CC]
        System.out.println(list2); // [AAA, BBB]

        // 需要注意数字类型的转换 不能直接使用 int 而要用 Integer 包装类
        // new int[] { 123, 456 } 这里整体会被认为是Object 而不是 数组
        List arr1 = Arrays.asList(new int[] { 123, 456 });
        System.out.println(arr1.size()); // 1

        List arr2 = Arrays.asList(new Integer[] { 123, 456 });
        System.out.println(arr2.size()); // 2
    }
}
