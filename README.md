# Javava

这个资源库主要记录了我初学*Java*的一个过程。因为属于是自学又没老师带，所以在搭建环境的时候感觉吃了不少苦头。

第一次学的时候虎头虎脑没有跟网上视频一样用Idea，而是用自己的vscode。所以在classpath的时候理解花了不少时间。

自己学习java目前大概是按照这样一个流程顺下来的。

- 基础语法
- 面向对象
- 异常
- 多线程
- 常用API
- 枚举
- 注解
- 集合
- IO流
- 泛型
- 反射
- JDBC

现在打算按照自己的理解，不定期的更新一下心得。（不按照顺序）

## 集合相关（Collection&Map）

这个一张特别详细的图，把实现和继承写的很详细。

![collections framework overview](https://www.codejava.net/images/articles/javacore/collections/collections%20framework%20overview.png)

但是有些上面的我并没有学。

我几乎就学的这些。

![コレクションのクラス構成](http://www.itsenka.com/images/development/java/java-collection01.gif)

感觉就足够了。

以下是个人理解区域

### Collection

```bash
|----Collection接口：单列集合，用来存储一个一个的对象
  |----List接口：存储有序的、可重复的数据。  -->“动态”数组,替换原有的数组
	   |----ArrayList：作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储 本质就是封装数组
	   |----LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高；底层使用双向链表存储 本质是链表
     |----Vector：作为List接口的古老实现类；线程安全的，效率低；底层使用Object[] elementData存储
  |----Set接口：存储无序的、不可重复的数据 
     |----HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
       |----LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历 对于频繁的遍历操作，LinkedHashSet效率高于HashSet.
     |----TreeSet：可以按照添加对象的指定属性，进行排序。
```

以上数据类型跟`equals()`,`hashCode`还有比较的*Comparable*，*Comparator* 关系。

**List** 因为需要`remove()`这些删除操作，但是在删除操作之前肯定要需要判断是否是一致。所以需要使用equals，如果不重写的话，就会默认使用的就是Object下的equals方法了。

**HashSet** 这个需要使用`equals()`还有`hashCode`两个方法。因为Set是不能重复的，那怎么确定是否重复呢。首先就要看的是hash值是否重复，其次看完hash值之后还要看equals是否重复。如果在第一步hash值就已经不是重复的情况下就无需对比equals了，这比几千个几万个使用equals进行对比遍历的方法更为高效。

**TreeSet** 最特殊的 因为使用的是这个数的结构。所以要求很多，比如元素的数据类型必须是一致的。且对比是否相等的方法使用的是自然排序 *Comparable* 和 自定义排序*Comparator* 

```java
Set ts = new TreeSet();
ts.add(23);
ts.add(56);
ts.add(-5);
ts.add(100);
ts.add(0);
System.out.println(ts); // [-5, 0, 23, 56, 100]
// 可以看到这里就是从小到大直接排序了。因为使用的是Integer的重写的
// public final class Integer extends Number implements Comparable<Integer>
```

Comparable 里面有方法 `compareTo()` 从小到大排序

```java
public int compareTo(Integer anotherInteger) {
        return compare(this.value, anotherInteger.value);
}
```

 但如果你不想用从小到大呢。难道你要重写类的方法吗。不用，这样就没有解耦性了。

那就要用到自定义排序，也就是让自己的类实现 *Comparator*  这个接口

整一个自然排序

```java
// 这里新建一个com的排序方式 （匿名）
Comparator com = new Comparator() {
  @Override
  // 按照年龄从小到大排列
  public int compare(Object o1, Object o2){
    if (o1 instanceof User && o2 instanceof User) {
          User u1 = (User) o1;
          User u2 = (User) o2;
          return Integer.compare(u1.getAge(), u2.getAge());
     } else {
          hrow new RuntimeException("输入的数据类型不匹配");
     }
  }
}
// com直接作为参数带入进去就可以
Set ts = new TreeSet(com);

```

上面的部分源码就是这样的

```java
public TreeSet(Comparator<? super E> comparator) {
        this(new TreeMap<>(comparator));
}
```

差不多就是这样的表格

| 数据类型       | 比较是否相同用的方法                                   | etc                            |
| -------------- | ------------------------------------------------------ | ------------------------------ |
| List           | *equals()*                                             | 最常用的就是ArrayList          |
| Set（HashSet） | *hashCode()* *equals()*                                | 最常用的就是HashSet            |
| Set（TreeSet） | *Comparable* → `compareTo`  *Comparator* → `compare()` | 数据类型要一样，因为底层红黑树 |

集合到数组

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Collection coll = new ArrayList();
        coll.add(1);
        coll.add(100);
        coll.add(2);
        System.out.println(coll);
        
        Object[] objArr = coll.toArray();
        for (int i = 0;i < objArr.length ; i++ ) {
            System.out.println(objArr[i]);
        } 
    }
}
```

**数组到集合**

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] arrA = {1 , 7, 9 , 2, 10};
        int[] arrB = {1 , 7, 9 , 2, 10};
        Collection collA = Arrays.asList(arrA);
        Collection collB = Arrays.asList(arrB);
        System.out.println(collA.size()); // 5
        System.out.println(collB.size()); // 1
    }
}
```

**集合到数组**

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] arrA = {1 , 7, 9 , 2, 10};
        int[] arrB = {1 , 7, 9 , 2, 10};
        Collection collA = Arrays.asList(arrA);
        Collection collB = Arrays.asList(arrB);
        System.out.println(collA.size()); // 5
        System.out.println(collB.size()); // 1
        
        Collection collString = Arrays.asList(new String[]{"String", "Array", "To", "Collection"});
        Iterator ite = collString.iterator();
        while(ite.hasNext()){
            System.out.println(ite.next());
        }
    }
}

```

### Map

