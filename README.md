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

```bash
|----Collection接口：单列集合，用来存储一个一个的对象
  |----List接口：存储有序的、可重复的数据。  -->“动态”数组,替换原有的数组
	   |----ArrayList：作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储 本质就是封装数组
	   |----LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高；底层使用双向链表存储 本质是链表
     |----Vector：作为List接口的古老实现类；线程安全的，效率低；底层使用Object[] elementData存储
  |----Set接口：存储无序的、不可重复的数据 
     |----HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
       |----LinkedHashSet：作为HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历 对于频繁的遍历操作，LinkedHashSet效率高于HashSet.
```

以上数据类型跟`equals()`,`hashCode`还有比较的*Comparable*，*Comparator* 关系。

