package com.map.exer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Map的常用方法2 主要是关于遍历的方法
 * 
 * 元视图操作的方法：
 * Set keySet()：返回所有key构成的Set集合
 * Collection values()：返回所有value构成的Collection集合
 * Set entrySet()：返回所有key-value对构成的Set集合

 * 总结：常用方法：
 *  添加：put(Object key,Object value)
 *  删除：remove(Object key)
 *  修改：put(Object key,Object value)
 *  查询：get(Object key)
 *  长度：size()
 *  遍历：keySet() / values() / entrySet()
 */
public class MapMethod2 {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("AA", 123);
        map.put("BB", 456);
        map.put("CC", 123);
        // 关于遍历Map 因为所有的key毕竟是由set构成的，所以set是可以遍历的

        // 1 遍历所有的key集 keySet
        Set set = map.keySet();
        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
        System.out.println("************华丽的分割线************");

        // 2 遍历所有的Value values()
        Collection<Integer> values = map.values();
        for (Object result : values) {
            System.out.println(result);
        }
        System.out.println("************华丽的分割线************");

        // 3-1 遍历所有的k-v
        Set<Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Entry<String, Integer>> ite2 = entrySet.iterator();
        while (ite2.hasNext()) {
            // 输出1
            // System.out.println(ite2.next());
            // 输出2 迭代器生成的是一个对象，需要强转到Entry
            Entry<String, Integer> entry = ite2.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }

        System.out.println("************华丽的分割线************");

        // 3-2 遍历所有的k-v
        // 思路，先拿到keySet 然后通过get方法 得到value
        Set<String> set2 = map.keySet();
        Iterator<String> ite3 = set2.iterator();
        while (ite3.hasNext()) {
            String key = ite3.next();
            Integer value = map.get(key);
            System.out.println(key + "==" + value);
        }

    }
}
