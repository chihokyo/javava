package com.objectarray.exer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
/**
 * 常用方法2
 * 
 * 1.contains(Object obj):判断当前集合中是否包含obj
 * 2.containsAll(Collection coll1):判断形参coll1中的所有元素是否都存在于当前集合中。
 * 3.remove(Object obj):从当前集合中移除obj元素。
 * 4.removeAll(Collection coll1):差集：从当前集合中移除coll1中所有的元素。
 * 5.retainAll(Collection coll1):交集：获取当前集合和coll1集合的交集，并返回给当前集合
 * 6.equals(Object obj):要想返回true，需要当前集合和形参集合的元素都相同。
 */
public class CollectionTest2 {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Amy"));
        coll.add(false);
        coll.add(456);
        coll.add(new Person("Tom", 30));

        // 1.contains(Object obj):判断当前集合中是否包含obj
        // 我们在判断时会调用obj对象所在类的equals()。
        boolean contains = coll.contains(123);
        System.out.println(contains); // true

        // 2 虽然是2个对象，但是这是否包含的话。得出的true，判断的是内容而非地址
        // 不是 == 而是equals
        System.out.println(coll.contains(new String("Amy"))); // true

        Person p = new Person("Jerry", 30);
        coll.add(p);
        System.out.println(coll.contains(p)); // true 这里肯定是，因为就是包含这个对象
        // 因为调用的是equals String自己是重写过的 比较的是结果
        // 但是下面的对象没有重写，如果Person类重写了 equals 这个方法 就可以重新判断数值，而非地址
        System.out.println(coll.contains(new Person("Jerry", 30))); // false
        System.out.println(coll.contains(new Person("Jerry", 30))); // ！重写equals 之后true
        // 关于调用了几次的问题，需要一个个去对比。
        // 就是拿 new Person("Jerry",30).equals(一个个对比的形参)) 这样去比较
        // 一旦找到了也就不会再去对比了 因为ArrayList是有序的

        System.out.println("**************华丽的分割线***************");

        // 3 containsAll
        Collection coll2 = Arrays.asList(123, 456); // 返回一个list list又是 Collection子类 多态
        boolean coll2Contains = coll.containsAll(coll2);
        System.out.println(coll2Contains); // true

        System.out.println("**************华丽的分割线***************");

        // 4 remove（） 要调用equals 先查找对 比 在进行删除
        Collection collRemove = new ArrayList();
        collRemove.add(123);
        collRemove.add(456);
        collRemove.add(new Person("Jerry", 20));
        collRemove.add(new String("Tom"));

        boolean result = collRemove.remove(123);
        System.out.println(result); // true
        boolean result2 = collRemove.remove(124);
        System.out.println(result2); // false
        System.out.println(collRemove); // [456, com.objectarray.exer.Person@840b7497, Tom]
        boolean result3 = collRemove.remove(new Person("Jerry", 20));
        System.out.println(result3); // true
        System.out.println(collRemove); // [456, Tom]

        // 5 removeAll（）
        Collection collRemoveAll = Arrays.asList(new Person("Jerry", 20));
        collRemove.removeAll(collRemoveAll); // 没有返回值，直接修改
        System.out.println(collRemove);

        // 6 retainAll() 修改调用的对象
        Collection collX = new ArrayList();
        collX.add(123);
        collX.add(456);
        collX.add(new String("Amy"));
        collX.add(false);
        collX.add(new Person("Tom", 30));
        System.out.println(collX);

        Collection collY = new ArrayList();
        collY.add(new String("Amy"));
        collY.add(false);
        collY.add(new Person("Tom", 30));
        // collX 和 collRemoveAll 交集之后 返回给 collX 无需增加变量赋值
        System.out.println(collX);
        System.out.println(collY);
        collX.retainAll(collY);
        System.out.println(collX); // [Amy, false, com.objectarray.exer.Person@27e0ed]

        // 7 equals(); 2个集合顺序不一样也不行，因为 ArrayList 是有序的
        // 要想返回 true 类型肯定是要相同的
        System.out.println(collX.equals(collY)); // true
    }
}

class Person {
    private String name;
    private int age;

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

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Person equals() ");
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

}