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

**TreeSet** 最特殊的 因为使用的是这个数的结构。所以要求很多，比如元素的<u>数据类型必须是一致</u>的。且对比是否相等的方法使用的是自然排序 *Comparable* 和 自定义排序*Comparator* 

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

**集合到数组**

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

**数组到集合1**

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

**数组到集合2**

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] arrA = {1 , 7, 9 , 2, 10};
        int[] arrB = {1 , 7, 9 , 2, 10};
        Collection collA = Arrays.asList(arrA);
        Collection collB = Arrays.asList(arrB);
        System.out.println(collA.size()); // 5 
        System.out.println(collB.size()); // 1 这里是1的原因是因为并没有自动拆箱。直接当Object算的
        
        Collection collString = Arrays.asList(new String[]{"String", "Array", "To", "Collection"});
        Iterator ite = collString.iterator();
        while(ite.hasNext()){
            System.out.println(ite.next());
        }
    }
}

```

**过滤一组数组中重复的值**

```java
public class Main {
    public static void main(String[] args) throws Exception {
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(3));
        List list2 = duplicateList(list);
        // 循环方法1
        Iterator ite = list2.iterator();
        while(ite.hasNext()){
            System.out.println(ite.next());
        }
        // 循环方法2
      	for (Object i : list2 ){
            System.out.println(i);
        }
    
    }
    
    public static List duplicateList(List list) {
      	// 新建一个空HashSet（因为Set不能有重复值 就用这个）
        HashSet hset = new HashSet();
        // 这个时候list已经过滤过然后给了hset
        hset.addAll(list);
      	// 返回一个过滤过后的list
        return new ArrayList(hset);
    }
}

```

**关于Set删除的一个点**

要明白HashSet 永远是先hashCode 然后在equals

```java
HashSet set = new HashSet();
Person p1 = new Person(1001, "AA");
Person p2 = new Person(1002, "BB");

set.add(p1);
set.add(p2);
System.out.println(set); // 肯定是AA BB 都包含

p1.name = "CC";
set.remove(p1);
System.out.println(set);// 这时候发现结果是BB CC 因为还是按照p1(CC)来判断的 当时添加的时候根本没有 所以没能remove成功

set.add(new Person(1001, "CC"));
System.out.println(set); // BB CC CC 这里是因为new的时候也是按照1001 CC来判断的，那个位置啥都没有 肯定添加成功

set.add(new Person(1001, "AA"));
System.out.println(set); // BB CC CC AA 这个时候因为判断的是1001 AA hashCode是过去了。但是在equals的时候发现是CC（↑改过了） 所以这个也能添加成功
```

### Map

三个孩子

- HashMap 最常用的键值对 可以存储null
  - LinkedHashMap 对比上面多加了链表
- TreeMap 键值对的树 （和上面TreeSet很像 自带排序）
- Hashtable 古老的实现方式 不能使用null 线程安全 
  - Properties 常用来处理配置文件 key和value都是String类型

HashMap的key很像Set value很像Collection

```
1 Map中的key:无序的、不可重复的，使用Set存储所有的key  
          ---> key 所在的类要重写 equals()和 hashCode() （以HashMap为例）
2 Map中的value:无序的、可重复的，使用 Collection 存储所有的value 
          ---> value 所在的类要重写 equals() 为什么不用重写hashCode 因为判断的时候可以通过key直接找到，也没必要统一成一样的value
3 一个键值对：key-value构成了一个Entry对象。Map中的 entry:无序的、不可重复的，使用Set存储所有的entry （Key都不重复了，entry整体肯定不可能重复啊）
```

**遍历所有key**

```java
Map<Integer, String> map = new HashMap<Integer, String>();
map.put(1, "123");
map.put(2, "456");
map.put(3, "789");
Set set = map.keySet();
Iterator ite = set.iterator();
while(ite.hasNext()){
	System.out.println(ite.next());
}
```

**遍历所有的value**

```java
Map<Integer, String> map = new HashMap<Integer, String>();
map.put(1, "123");
map.put(2, "456");
map.put(3, "789");
Collection<String> values = map.values();
for (String s : values){
	System.out.println(s);
} 
```

**遍历所有的k-v**

方法1

```java
Map<Integer, String> map = new HashMap<Integer, String>();
map.put(1, "123");
map.put(2, "456");
map.put(3, "789");
Set<Entry<Integer, String>> entrySet = map.entrySet();
Iterator<Entry<Integer, String>> ite = entrySet.iterator();
while(ite.hasNext()){
  Entry<Integer, String> entry = ite.next();
  System.out.println(entry.getKey() + " : " + entry.getValue());
}
```

方法2

```java
Map<Integer, String> map = new HashMap<Integer, String>();
map.put(1, "123");
map.put(2, "456");
map.put(3, "789");
Set<Integer> set =  map.keySet();
Iterator ite = set.iterator();
while(ite.hasNext()){
  // 拿到key
	Object key = ite.next();
  String value = map.get(key);
  System.out.println(key + "==" + value);
}
```

**关于 Collection PK Collections**

一言以蔽之。Collection是一个**集合数据类型**的接口。Collections 是一个**工具**接口。里面好多静态方法可以服务于其他数据类型。

具体看这个 [JAVA中Collection和Collections的区别](https://www.cnblogs.com/shikamaru/p/8926311.html)

## 面向对象

### 1. **继承**

子类继承父类。子类就继承了父类所有的属性和方法。虽然private不可以直接获取，但可以通过父类里面其他方法调用。

其实子类已经获取了父类的结构，但是因为封装性的影响，并不能直接调用。

```java
private String name;
public String getName(){
  return this.name
}
// 比如子类不可以直接.name
// 但是可以调用父类的getName方法照样可以获取父类的name
```

#### 关于重写

<u>方法名</u>和<u>形参列表</u>需要一样。【理解成覆盖比较好容易理解】

父类的private不能重写。比如。↓

```java
// 父类写
private void show(){
  System.out.println("p show");
}
// 子类写 这个时候是可以调用并且成功的，因为这样只是把它当做子类的方法。而不会当做成重写
public void show(){
  System.out.println("c show");
}
// NG 这样就不行，指名了是重写覆盖。肯定会出错。
@Override
public void shwoshow
  System.out.println("c show");
}

```

#### 返回值类型

| 父类                   | 子类                                                         |      |
| ---------------------- | ------------------------------------------------------------ | ---- |
| void                   | void                                                         |      |
| A类                    | A类or A类的子类                                              |      |
| 基本数据类型（double） | 必须是相同的（double）不能写int，不能自动类型提升 return (double)int 这样可以 |      |

```java
// 父类
public Object info(){
  return null;
}
// 子类
public String info(){
  return "c String"
}
```

异常类型 和 返回值类型一样。绝对不能。大于父类。比如父：Exception。子可以RuntimeException。

**static 方法不可以被重写。** static根本不能叫重写。因为随着类加载而加载。

#### 子类如何调用已经重写父类的方法？

*super()*

```java
class Person {
    String name;
    int age;
    public Person () {
        
    }

    public Person (String name, int age){
        this.name = name;
        this.age = age;
    }
    public void eat() {
        System.out.println("Person eat");
    }

    public void walk(int distance) {
        System.out.println("Person walk : " + distance);
    }
}

class Student extends Person {
    String major;
    public Student () {
        
    }
    
    public void eat() {
        super.eat(); // 这里调用父类
        System.out.println("Student eat");
    }
    
    public Student (String major){
        this.major = major;
    }
    public void study() {
        System.out.println("Student study: " + major);
    }
}


```

super可以调用在某些情况下和this可能会冲突 属性和方法不同。

属性不会存在覆盖这一说。也就是说此时内存里既有父类的属性也有子类的属性。2者都会存在。

```java
class Person {
    String name;
    int age;
  	int id;

 class Student extends Person {
    int id;
 
  pubßlic void show(){
    // 因为子类和父类并没有冲突。所以这个时候这俩是一样的this还有super
    // this的时候会优先在自己的范围内查找，没有就去找super。而super就是直接去找
    System.out.println("name = " + this.name + ", age = " + super.age);
    // 这个就出问题了 id既然子父类都有，那么使用什么都要用this or super指名
    System.out.println("id = " + id);
  }
```

证明一下上面的东西

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Student s = new Student("Programing");
        s.show();
    }
}

class Person {
    String name;
    int age;
    int id = 1001;
    public Person () {
    }
    public Person (String name, int age){
        this.name = name;
        this.age = age;
    }
}

class Student extends Person {
    String major;
    int id = 1002;
    public Student () {
        
    }

    public Student (String major){
        this.major = major;
    }
    
    public void show() {
        System.out.println("name = " + this.name + ", age = " + super.age);
        // System.out.println("id = " + super.id); // 1001
        // System.out.println("id = " + this.id); // 1002
        System.out.println("id = " + id); // 1002 默认1002 也就是自己的
    }
}
```

> 结论就是。可以在子类的方法或者构造器中。通过super属性super方法来调用父类中声明的属性or方法。但是通常情况下会省略super。
>
> 特殊情况。在子类和父类属性不幸重名的情况下。想要在子类调用父类的属性or方法的时候，必须显示的使用super属性方式。
>
> 特殊情况。上面的在方法的情况下。如果父类没有super会一层层向上找，直到找到有父类super的情况
>
> super 关键字
>       1 super 理解为父类
>       2 super 可以用来调用：属性，方法，构造器
>       3 super 怎么用呢 
>              **3-1【关于属性】** 子父类拥有相同的属性 this默认为当前对象,需要调用父类的 super关键字
>              如果在自己的没找到，本身找不到就会想前找。这样就会层层的向上找 this往往都会省略掉
>              name 和 this.name 区别就是 第一个会在自己找不到的情况下想前层层去找。但是this.name直接就用自己的了
>              **3-2 【关于方法】**也是一样的，同理。当子类和父类方法相同的时候，需要显式的进行super调用父类的方法，否则就会默认
>                    使用子类的方法。
>              **3-3【关于构造器】**
>                    1 可以在子类的构造器中进行显示的用 super 调用父类构造器
>                    2 super（形参列表） 必须声明在子类构造器的**首行**
>                    3 按照构造器的原理，所以this(),super() 只能2选1 不能同时出现
>                    4 有时候写子类的构造器 默认 IDE就设置了一个super 父类的空参构造器
>                    5 父类没有空参构造器的时候，子类调用有时候可能会出错
>                    6 在多个构造器中，至少有一个类的构造器中使用了super(形参列表)调用了父类的构造器

**super 调用构造器**

在没有显示写this or super构造器的时候，系统默认给了super() ,如果父类没有空参构造器的时候。

子类也没有显示写出来this or super 就会报错的原理就在这里。

```java
class Person {
    String name;
    int age;
    public Person () {
    }

    public Person (String name, int age){
        this.name = name;
        this.age = age;
    }
}

class Student extends Person {
    String major;
    public Student () {
        
    }
  	public Student (String major) {
      	super(); // 在没有this or super的情况下其实默认省略了super
        this.major = major;
    }

    public Student (String name, int age, String major){
        super(name, age); // 这里显式的调用了super↑前提是父类必须要有
        this.major = major;
    }
    public String toString(){
        
    }
}
```

#### 为什么super和this调用语句不能同时出现？

因为都要在首行。

#### 为什么super or this 调用语句只能在首行？

无论什么构造器创建子类对象，都需要初始化父类。因为需要继承父类的方法和属性，因此子类必须先初始化父类。

#### 子类实例化的过程是什么？

子类对象实例化的全过程
  从结果上看 继承性
      子类继承父类之后，就获取了父类中声明的【属性或方法】
      创建子类的对象，在堆空间中，就会加载**所有**父类中声明的属性（全部的父类，无论直接间接。）

  从过程上看
      当我们通过子类的构造器创造子类对象时，一定会直接or间接的调用父类的构造器,层层递进。
      直接调用了java.lang.Object类中空参构造器位置。
      正因为加载过所有的父类结构，所以才可以看见内存中有父类的结构。子类才能进行调用。
      虽然说这么多，但自始至终其实只有一个对象。就是 new 出来的 ，不包括父类其他的对象的。
      因为父类并没有new，更没有父类对象的地址

个人理解，就是继承相当于把所有父类吸收到自己的【模子】里。new的时候啥都有。

### 2. 多态性

#### 对象多态性是什么？

父类的引用指向子类的对象。`Person p = new Man();` 

#### 虚拟方法「Virtual Method Invocation」调用是什么？

当调用父类同名同参数的方法时，实际执行的是子类重写的父类方法。

子类定义了和父类同名同参数的方法，在多态的情况下。此时的父类就是虚拟方法。父类根据不同的子类对象。动态调用属于该子类的方法。这个方法在编译器是无法确定的，【动态绑定】也就是说多态是一个运行时行为，只有运行的时候才能确定

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Person p = new Man();
        p.eat();
        p.walk();
    }
}

class Person {
    String name;
    int age;
    int id = 100;
    public void eat() {
        System.out.println("Person eat");
    }
    public void walk() {
        System.out.println("Person walk");
    }
}

class Man extends Person {
    boolean isSmoking;
    int id = 200;

    public void fight() {
        System.out.println("Man can fight");
    }
    @Override
    public void eat() {
        System.out.println("Man eat more");
    }
    @Override
    public void walk() {
        System.out.println("Man walk slow");
    }
}

class Woman extends Person {
    boolean isCute;

    public void beBeauty() {
        System.out.println("Woman Beauty Girl");
    }
    @Override
    public void eat() {
        System.out.println("Woman eat good");
    }
    @Override
    public void walk() {
        System.out.println("Woman walk fast");
    }
}
```

#### 验证一下编译时行为还是运行时行为？

一言以蔽之，就是在你读代码的时候其实你根本不知道到底执行的是什么对象。编译的时候无法确定。真正执行起来的时候就可以确定了。

```java
public class Main {
    public static void main(String[] args) throws Exception {
      // 生成一个随机数
        int key = new Random().nextInt(3);
        System.out.println(key);
      // 根据随机数生成对应的对象
        Animal animal = getInstance(key);
        animal.eat();
    }
   
    public static Animal getInstance(int key) {
        switch(key) {
            case 0:
                return new Cat();
            case 1:
                return new Dog();
            default:
                return new Sheep();
        }
    }
}

class Animal{

    public void eat() {
        System.out.println("Animal eat");
    }
}

class Dog extends Animal{
    @Override
    public void eat() {
        System.out.println("Dog eat bones");
    }
}

class Cat extends Animal{
    @Override
    public void eat() {
        System.out.println("Cat eat Fish");
    }
}
class Sheep extends Animal{
    @Override
    public void eat() {
        System.out.println("Sheep eat Fish");
    }
}
```

重载的话，在编译的时候就已经确定了要调用什么方法。就是**静态绑定。**

而多态必须在调用的那一刻才知道具体调用的数值，所以是**动态绑定。** 如果不是动态绑定就不是多态。！！

#### 那么可以调用父类里没有的方法吗？

NO。编译的时候看的是左边，声明的什么类型就是什么类型。

`Person p = new Man();`

左边 编译的时候看Person p 所以Person没有的方法根本不能用

右边 执行的时候但用的是左边。

#### 多态的前提是什么？

- 有继承关系（子父类等等）
- 要有方法的重写。（子类基本都会重写，如果没有重写，那直接子类不就得了。）

#### 为什么要使用多态？

省略多个重载方法。比如equals(Object obj) 如果没有多态。那么每一次调用equals难道都要new一个Object吗

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Main ani = new Main();
        ani.func(new Dog());
        ani.func(new Cat());
    }
    // 1 有多态的情况下，可以直接把new Dog就这么当参数传给func
    public void func(Animal animal) { // Animal animal = new Dog();
        animal.eat();
        animal.wow();
    }
    // 2 没有有多态的情况下 就要像下面一样写
    
    public void func(Dog dog) {
        dog.eat();
        dog.wow();
    }
    public void func(Cat cat) {
        cat.eat();
        cat.wow();
    }
    // ... 也就说声明什么类型的，就只能new这个类型对象了 不然要重载写多少遍啊。。
}

class Animal{

    public void eat() {
        System.out.println("Animal eat");
    }

    public void wow() {
        System.out.println("Animal wow");
    }
}

class Dog extends Animal{
    @Override
    public void eat() {
        System.out.println("Dog eat bones");
    }
    @Override
    public void wow() {
        System.out.println("Dog wangwang");
    }
}

class Cat extends Animal{
    @Override
    public void eat() {
        System.out.println("Cat eat Fish");
    }
    @Override
    public void wow() {
        System.out.println("Cat miaomiao");
    }
}
```

或者比如多个数据库

```java
public void doData(Connection con) { // con = new MySQLConnection();
        // 规范的步骤操作数据 
        // 无论什么数据库（Sql,mysql sqlite）进入到这里面都一样
        // 这样就在父类定义了3个步骤,真正的连接都可以
        // 这样子类就一定会重写。真正使用起来的时候，你只要把你的子类的方法进行重写就可以了
        // 类似于这样
        // con = new MySQLConnection();
        // con = new OracleConnection();
        con.method1(); // 这里就是调用的子类的方法了
        con.method2();
        con.method3();
    }
} 
```

#### 多态性在属性的体现呢？

和方法不同。对象的多态性<u>只适用于方法，不适用于属性</u>。属性不存在多态性。所以Person p = new Man();

类型是谁就用谁的属性 编译运行都要看左边

```java
class Person {
  int id = 1;
}
class Man extend {
  int id = 2;
}
Person p = new Man();
System.out.prinln(p.id); // 1
```

#### 多态如何调用子类特有的方法？

有了多态之后，内存中实际加载了子类的方法和属性。由于变量类型是父类类型，导致编译只能识别父类的方法。

答案：强行转换。

```java
Person p = new Man();
Man m1 = (Man) p; // 强制转换类型
```

基本类型

- 强制类型转换 double → `(double)int`
- 自动类型提升 int → double

引用数据类型**(前提必须要有关系（继承，实现啥的）)**

- 向上走 多态 `Person p = new Man();`
- 向下走 使用`instanceof()`判断后强制类型转换 `Man m1 = (Man)p`

测试一下走起。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Animal animal = new Dog();
        Dog d = (Dog) animal;
        System.out.println(d.id);
    }
}

class Animal{
    String name;
    public void eat() {
        System.out.println("Animal eat");
    }
}

class Dog extends Animal{
    int id = 12;
    @Override
    public void eat() {
        System.out.println("Dog eat bones");
    }
}
```

为什么可以了呢。其实在打印的时候就可以发现。实际上在内存里都是存在的。类型@地址。有一个类型的限制。所以是找不到的，但是强转之后，类型发生了变化，就是可以找到了

#### 如何避免强转风险呢？

为了避免在向下转型的时候 ClassCastException的异常。可以在向下转型执行，进行判断。

之前先进行关键字 instanceof 判断 注意这是关键字

a instanceof A 判断a是否是A的实例 如果是true 否则 false

```java
 public static void main(String[] args) throws Exception {
        Animal animal = new Dog();
        if(animal instanceof Dog ) {
            Dog d = (Dog) animal;
            System.out.println("!!!");
            System.out.println(d.id);
        }
    }
```

```
如果 
a instanceof A  是true 
a instanceof B  是true
那么B就是A的父类。【B一定要大于A】
a instanceof Object  永恒true
```

```java
Person p = new Person();
Man m = (Man)p; //肯定错啊，这俩根本没关系。上面new的是Person

Object obj =  new Woman();
Person p = (Person)obj; // 可以的 先向上提升之后，在强转向下。

Man m = new Woman(); // NG 这俩根本没关系，平级
```

多态的属性or方法的终极练习

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Sub s = new Sub(); // 不是多态 
        System.out.println(s.count);// 20
        s.display();// 20
        
        Base b = s;
        System.out.println(b == s); // 这里是引用数据类型== 判断地址 地址已经在前面赋值过去了 true
        System.out.println(b.count);// 这里判断属性，属性是看左边。Base的 于是 10 
        b.display();// 这里是方法 方法已经重写覆盖 20
    }
   
}

class Base {
    int count = 10;
    public void display() {
        System.out.println(this.count);
    }
}

class Sub extends Base{
    int count = 20;
    public void display() {
        System.out.println(this.count);
    }
}
```

再来一道练习题

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Base b = new Sub();
        b.add(1, 2, 3); // sub int[] arr
        
        Sub s = (Sub)b;
        s.add(1, 2, 3); // sub int a,b,c
    }
}

class Base {
    public void add(int a, int... arr) {
        System.out.println("base");
    }
}

class Sub extends Base {
    public void add(int a, int[] arr) {
        System.out.println("sub int[] arr");
    }
    // 这个不是重写
    public void add(int a, int b, int c) {
        System.out.println("sub int a,b,c");
    }
}
```

### 3. Object类

无属性

1个空参构造器

一堆方法

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Base b = new Base();
        System.out.println(b.getClass()); // class Base
        System.out.println(b.getClass().getSuperclass()); // class java.lang.Object
    }
}
```

| Modifier and Type  | Method                                | Description                                                  |
| :----------------- | :------------------------------------ | :----------------------------------------------------------- |
| `protected Object` | `clone()`                             | Creates and returns a copy of this object.                   |
| `boolean`          | `equals(Object obj)`                  | Indicates whether some other object is "equal to" this one.  |
| `protected void`   | `finalize()`                          | **Deprecated.**The finalization mechanism is inherently problematic. |
| `Class<?>`         | `getClass()`                          | Returns the runtime class of this `Object`.                  |
| `int`              | `hashCode()`                          | Returns a hash code value for the object.                    |
| `void`             | `notify()`                            | Wakes up a single thread that is waiting on this object's monitor. |
| `void`             | `notifyAll()`                         | Wakes up all threads that are waiting on this object's monitor. |
| `String`           | `toString()`                          | Returns a string representation of the object.               |
| `void`             | `wait()`                              | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*. |
| `void`             | `wait(long timeoutMillis)`            | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*, or until a certain amount of real time has elapsed. |
| `void`             | `wait(long timeoutMillis, int nanos)` | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*, or until a certain amount of real time has elapsed. |

#### == PK equals()

```java
public static void main(String[] args) throws Exception {
        int i = 10;
        int j = 10;
        double d = 10.0;
        char c = 10;
        System.out.println(i == j); // true 虽然有类型提升，但是比较的还是数值
        System.out.println(i == d); // true
        System.out.println(i == c); // true
        
        char c1 = 'A';
        char c2 = 65;
        System.out.println(c1 == c2); // true
}
```

| 数据类型     | ==       | equals()                                                     |
| ------------ | -------- | ------------------------------------------------------------ |
| 基本数据类型 | 比较数值 | 因为是方法，不能使用基本类型。【除非包装类】                 |
| 引用数据类型 | 比较地址 | 没重写。和== 一样。默认用父类【Object 比地址】<br />重写。看怎么重写的。基本上是内容。 |

#### 如何重写equals()?

基本上用的方法都是自动生成。

先判断类型 后判断是否相等（这个相等基本数据类型用== 引用数据类型`equals()`）

```java
public boolean equals(Object obj) {
    // 地址都一样了，那肯定一样。
		if(this == obj) {
      return true;
    }
   // 判断类型
  if(obj instanceof Order ){
    Order order = (Order)obj;
    // OK
    return this.orderId == order.orderId && this.orderName.equals(order.orderName);
    // NG
    return this.orderId == order.orderId && this.orderName == order.orderName;
  }
}
```

#### `toString()`是干嘛的？

-  **Object 类的 toString 方法返回一个字符串，该字符串由类名（对象是该类的一个实例）、at 标记符“@”和此对象哈希码的无符号十六进制表示组成**。

  源码 

  ```java
  
  public String toString() {
          return getClass().getName() + "@" + Integer.toHexString(hashCode());
  }
  ```

  - 在String与其他数据类型进行连接的时候自动调用`toString()`方法
  - 重写`toString()`方法比较好
  - 基本类型数据转换为String类型的时候，调用了包装类的`toString()`方法

```java
Date now = new Date();
        System.out.println("Now: " + now);
        System.out.println("Now: " + now.toString()); // 上下一样
        
        String s1 = "Hello";
        System.out.println(s1); // Hello
        
        int i1 = 199;
        System.out.println(i1); // 199
}
```

#### 如何重写toString？

虽然系统能自动给。基本上就是我这样的写法。

```java
public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", balance='" + getBalance() + "'" +
            ", annualInterestRate='" + getAnnualInterestRate() + "'" +
            "}";
}
```

### 4. 包装类

#### **为什么要有包装类？**

因为普通的基本数据类型不能使用Java面向对象里类高大上的各种方法。不是类。怎么.?

比如`equals(Object obj）` 这个方法里面只能进去Object

| Primitive Data Type | Wrapper Class   |
| ------------------- | --------------- |
| char                | **Character{}** |
| byte                | Byte{}          |
| short               | Short{}         |
| int                 | **Integer{}**   |
| long                | Long{}          |
| float               | Float{}         |
| double              | Double{}        |
| boolean             | Boolean{}       |

所有数字的类都有一个父类*Number*。

```
java.lang.Object
	java.lang.Number
		java.lang.Integer
// Number实现了接口
All Implemented Interfaces:
Serializable
// Number主要的子类
Direct Known Subclasses:
AtomicInteger, AtomicLong, BigDecimal, BigInteger, Byte, Double, DoubleAccumulator, DoubleAdder, Float, Integer, Long, LongAccumulator, LongAdder, Short
```

看源码就知道包装类的本质，其实就是把基本数据类型作为属性进行封装了而已。

#### 基本类型，包装类，String怎么互相转换啊？

[Github一些代码](https://github.com/chihokyo/javava/commit/fa350087282c71fb942a564d09ea5df4b206460e)

基本类型 → 包装类

- `valueOf()`

- 自动装箱 

  ```java
  ArrayList<Integer> integerArrayList = new ArrayList<>(); integerArrayList.add(1); // 只能放class 却放了1 1 不用转换成Integer
  
  int intP1 = 100;
  Integer intP2 = intP1;
  ```

包装类 → 基本类型

- `intValue()` `floatValue` ...xxxValue
- 自动拆箱

```java
ArrayList<Integer> integerArrayList = new ArrayList<>(); integerArrayList.add(1);
int i = integerArrayList.get(0); // int i 而不是Integer i

Boolean boolObj2 = Boolean.valueOf(false);
boolean bool2 = boolObj2;
System.out.println(bool2); // false
```

基本类型 → String

包装类 → String

因为自动装箱和拆箱的原因，基本类型和包装类几乎都可以算作是一个整体了。

所以一起来看

```java
// 方法1 连接运算 ”“
Integer inteObj = Integer.valueOf(678);
String inteStr = inteObj + "";
System.out.println(inteStr); // 678
// 方法2 valueOf
Integer inteObj2 = Integer.valueOf(567);
String inteStr2 = String.valueOf(inteObj2);
System.out.println(inteStr2); // 567
// 方法3 toString方法

```

String → 基本类型 

`parseXXX(String)`

```java
public static void main(String[] args) throws Exception {
        String s = "124";
        int i = Integer.parseInt(s);
        System.out.println(i + 1);
}
```

 String→ 包装类

#### 包装类这么好为什么还要转换成基本类型？

因为还想搞一些低俗的加减乘除操作。

```java
public static void main(String[] args) throws Exception {
        Integer intObj = Integer.valueOf(123);
        int i1 = intObj.intValue();
        System.out.println(i1 + 100); // 223
}
```

#### 为什么要使用自动装箱boxing，拆箱unboxing ？

为了怕麻烦。因为基本类型经常和包装类进行转换。同上↑

#### 如何使用自动装箱呢？

```java
int num1 = 10;
Integer num2 = num1; // 怎么可以直接赋值给一个类呢 自动装箱

boolean b1 = true;
Boolean b2 = b1; // 同理

int num = 10;
method(10);
// 常理来说 10是无法直接当参数的 应当转换成Integer作为Object的子类
// 但是自动装箱功能就无需转换了
public void method(Object obj){
	System.out.printIn(obj);
}
```

#### 如何使用自动拆箱呢？

```java
int i1 = 16;
Integer i2 = i1;
System.out.println(i2.toString());
```

#### 包装类练习题

```java
// 1
public static void main(String[] args) throws Exception {
  			// 三元运算符需要类型一致，所以编译自动提升成了double
        Object o1 = true ? new Integer(1) : new Double(2.0);
        System.out.println(o1); // 1.0
}
// 2
public static void main(String[] args) throws Exception {
        Object o2;
        if (true)
            o2 = new Integer(1);
        else
            o2 = new Double(2.0);
        System.out.println(o2); // 1
}
// 3
public static void main(String[] args) throws Exception {
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(i == j); // false
        
        Integer m = 1;
        Integer n = 1;
        System.out.println(m == n); // true
        // 因为有个Integer cache 缓存是 -128 -127 这个范围内提前加载到缓存
        // 超过了就要new
        Integer x = 128;
        Integer y = 128;
        System.out.println(x == y); // false
}
```



