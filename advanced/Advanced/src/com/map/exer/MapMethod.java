package com.map.exer;

import java.util.HashMap;
import java.util.Map;

/**
 * Map的常用方法
 *  添加、删除、修改操作：
    Object put(Object key,Object value)：将指定key-value添加到(或修改)当前map对象中
    void putAll(Map m):将m中的所有key-value对存放到当前map中
    Object remove(Object key)：移除指定key的key-value对，并返回value
    void clear()：清空当前map中的所有数据
    元素查询的操作：
    Object get(Object key)：获取指定key对应的value
    boolean containsKey(Object key)：是否包含指定的key
    boolean containsValue(Object value)：是否包含指定的value
    int size()：返回map中key-value对的个数
    boolean isEmpty()：判断当前map是否为空
    boolean equals(Object obj)：判断当前map和参数对象obj是否相等
 * 
 */
public class MapMethod {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<String, Integer>();

        // 1 添加 put()
        map.put("AA", 123);
        map.put("AA", 456);
        map.put("BB", 123);
        System.out.println(map); // {AA=456, BB=123}

        // 2 修改 put 相同的key就会进行替换
        map.put("BB", 1000);
        System.out.println(map); // {AA=456, BB=1000}

        // 3 添加一个map putAll()
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("CC", 78);
        map1.put("DD", 66);
        map1.put("AA", 66); // 相同的Key会被覆盖
        map.putAll(map1);
        System.out.println(map); // {AA=66, BB=1000, CC=78, DD=66}

        // 4 remove() 移除 返回移除的value 没有就返回null
        Object result = map.remove("AA");
        Object result2 = map.remove("AA");
        System.out.println(result);
        System.out.println(result2);
        System.out.println(map);

        // 5 clear() 清空数据，而非清空map
        map.clear();
        System.out.println(map.size()); // 0
        System.out.println(map); // {}

        // 6 get() 获取指定key对应的value
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("CC", 78);
        map2.put("DD", 66);
        map2.put("AA", 66);
        Object obj = map2.get("AA");
        System.err.println(obj); // 66

        // 7 containsKey 是否包含指定的key
        System.out.println(map2.containsKey("CC")); // true
        System.out.println(map2.containsKey("BB")); // false

        // 8 containsValue 是否包含指定的value
        System.out.println(map2.containsValue(100)); // false

        // 9 size(); 返回键值对的个数
        System.out.println(map2.size()); // 3

        // 10 isEmpty
        System.out.println(map2.isEmpty()); // false

        // 11 equals()
        System.out.println(map2.equals(map)); // false
        Map<String, Integer> map3 = new HashMap<String, Integer>();
        map3.put("CC", 78);
        map3.put("DD", 66);
        map3.put("AA", 66);
        System.out.println(map2.equals(map3)); // true

    }
}
