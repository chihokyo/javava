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

### 5. static关键字

可以用于 属性 方法 代码块 内部类

直接优先加载到内存里，实例都是公用这一份。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        
        Animal a1 = new Animal();
        Animal a2 = new Animal();
        a1.name = "Cat";
        a2.name = "Dog";
        
        a1.home = "China";
        a2.home = "Japan";
        
        System.out.println(a1.name); // Cat
        System.out.println(a2.name); // Dog
        System.out.println(a1.home); // Japan
        System.out.println(a2.home); // Japan
	      System.out.println(Animal.home); // Japan
    }
}

class Animal {
    static String home;
    String name;
}
```

#### 静态变量的好处？

直接调用，无需新建对象。比如 `Math.PI` 多用于工具类啥的。

#### 静态方法是？

理论上和上面的变量一样。独占一份空间。可以直接调用。但是要注意。

static 只能调用 static的变量or方法。很简单啊。因为静态都是先于实例加载的，那肯定调用不到。比如。

**静态方法 只能调用 静态方法 or 静态属性**

**静态方法内不能使用this super**

**静态结构，没有特别声明的，都是省略的类。`Class.field` or `Class.method()`**

```java
public class Main {
    public static void main(String[] args) throws Exception {
        // Animal.infoA(); NG
        
        Animal.infoB();
    }
}

class Animal {
    static String home;
    String name;
    // public static void infoA(){
    //     // name 归对象所有 所以无法进行调用 方法本身就是错的
    //     System.out.println("Name is " + name); NG
    // }
    public static void infoB(){
        // name 归对象所有 所以无法进行调用
        System.out.println("Home is " + home); // Home is null
    }
}
```

都可以从生命周期来考虑。类产生-类变量or类方法-对象产生-对象变量or方法-对象消亡-类消亡

#### 开发中如何确定属性or方法是静态的？

多个对象大家一起共享的一般是静态属性。

如果操作静态属性的一般就是静态方法。

或者是工具类的种种一般都是静态的。`Math,Arrays,Collection`

#### 写一个简单的static应用（制造圆类）

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Circle c1 = new Circle();
        Circle c2 = new Circle();
        Circle c3 = new Circle(2.1);

        System.out.println("c1.id " + c1.getId());
        System.out.println("c2.id " + c2.getId());
        System.out.println("c3.id " + c3.getId());
        System.out.println("现在总共有圆的个数是: " + Circle.getTotal());
    }
}

class Circle {
    private int id;
    private double radius;
    
    private static int total;
    private static int init = 1001; // static 所有对象共享的 去掉之后所有对象都是1001 那就是错的了
    
    public Circle () {
        id = init++; // 自增之后多个对象共享 避免id重复
        total++;
    }
    
    public Circle (double radius) {
        // 重写前
        // id = init++;
        // total++
        // 重写后
        this();
        this.radius = radius;
    }
    
    public double getRadius(){
        return radius;
    }
    public int getId(){
        return id;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    public void setId(int id){
        this.id = id;
    }
    // 获取总数，这时候用static 是因为操作的属性也是static
    public static int getTotal(){
        return total;
    }
    
}

```

#### 什么是单例模式？

按照我现在的水平，我的理解就是一个类只能有一个实例对象。

如何保证一个类只能有一个实例对象呢？

翻译过来就是只能new一次之后不能在继续new

- **构造器设置成private →外部无法new**
- **外部无法new 内部必须要能new → 因为不new连一个对象都没有**
- **要能用这个对象，虽然是独生子，但也是可以使用的。→ 提供公共方法来返回类对象。**

```java
public class Main {
    public static void main(String[] args) throws Exception {
      	Bank b1 = Bank.getinstance();
				Bank b2 = Bank.getinstance();
      
    }
}

class Bank {
  // 1 私有化类构造器
  private Bank(){
    
  }
  // 2 内部创建类的对象 这里已经创造好了 并且是私有的
  private Bank instance = new Bank();
  // 4 必须是静态的
  private static Bank instance = new Bank();
  // 3-A 提供公共的方法，返回类的对象A　※这里有瑕疵。
  public Bank getInstance(){
    return instance;
  }
  // 3-B 如果我想调用下面的getInstance()  会发现必须要 new 
  // 但是下面给的属性已经new了。这里就不能new了。不能new怎么调用啊
  // 于是就改成静态的就可以new了 ※这里依然有瑕疵。
  public static Bank getInstance(){
    return instance;
  }
  
 // 3-C 瑕疵就在于instance不是static的 静态方法里怎可调用非static
  	
}
```

最后的结局就是 

```java
//
public class Main {
    public static void main(String[] args) throws Exception {
        Bank b1 = Bank.getInstace();
        Bank b2 = Bank.getInstace();
        System.out.println(b1 == b2);
    }  
}

class Bank {
    private Bank(){
    }
    private static Bank instance = new Bank();
    public static Bank getInstace(){
        return instance;
    }
}
```

下面还有一个版本是不用提前就进行的

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Bank b1 = Bank.getInstace();
        Bank b2 = Bank.getInstace();
        System.out.println(b1 == b2); //true 
    }
}

class Bank {
    private Bank(){
    }
  // 这种方式可以延迟对象的创建 无需加载 但此种方法非线程安全
    private static Bank instance = null;
    public static Bank getInstace(){
        if(instance == null) {
     				// 判断进入到这里之后 由于堵塞等原因 可能有多个判断都进入
           // 生成了多个实例
            instance = new Bank();
        }
        return instance;
    }
}
```

日网这里还有好多好多

https://qiita.com/Munchkin/items/b7a90dd2c262e5ee0ada

#### 为什么要用单例模式啊？

为了减少系统性能开销。比如读取一些配置资源，其实一份就够了永久贮存在内存里就够了。没必要多份。

#### 关于Main函数

main也是静态方法 所以无法调用非静态属性pr方法

也是入口函数

### 6. 代码块（初始化用）

#### 代码块作用是什么？

初始化类和对象

#### 可以有修饰符吗？

只能static 其他啥都不行

#### 建议分开写吗？

不建议。反正都要按照顺序执行。

#### static和非static有区别吗？

有 static 在类创建的时候进行加载。

非static在对象创建的时候加载。

#### 为什么要用代码块？

主要是用于一些初始化操作。比如数据库连接和配置文件读取。

顺便解决一个我内心的小问题。

```java
class Person {
   int age;
   age += 1;
  // 像上面那种age+=1这种写法，其实是绝对NG的。
  // 可以在函数内部做操作，但是在改写属性的地方是不能做一些操作的
  // 比如在上面不仅仅写操作，还写代码块 if语句什么的，都是不可以的。
  // 如何解决这个问题，就要使用代码块。
  private Person(){
    
  }
}
```

> 静态代码块
> 	>内部可以有输出语句
> 	>随着类的加载而执行,而且只执行一次
> 	>作用：初始化类的信息
> 	>如果一个类中定义了多个静态代码块，则按照声明的先后顺序执行
> 	>静态代码块的执行要优先于非静态代码块的执行
> 	>静态代码块内只能调用静态的属性、静态的方法，不能调用非静态的结构
>
> 非静态代码块
> 	>内部可以有输出语句
> 	>随着对象的创建而执行
> 	>每创建一个对象，就执行一次非静态代码块
> 	>作用：可以在创建对象时，对对象的属性等进行初始化
> 	>如果一个类中定义了多个非静态代码块，则按照声明的先后顺序执行
> 	>非静态代码块内可以调用静态的属性、静态的方法，或非静态的属性、非静态的方法

#### 都可以赋值的情况下优先级是？

静态块,main(),构造（非静态）块,构造方法。

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210120223928.png)

#### 关于父子构造器呢？

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210120230850.png)

### 7. final 关键字

| 修饰对象 |                      |                                             |
| -------- | -------------------- | ------------------------------------------- |
| Class    | 不能继承了【太监类】 | String System StringBuffer 都是不能被继承的 |
| method   | 不能重写了           | Object类中的getClass()                      |

关于变量的修饰 稍微麻烦点。

属性 就是Field 意思就是写在Class里的那个

**有final的地方一定要初始化赋值！！**

```java
class Person {
  // 1 显示初始化 OK
  final int AGE = 5;
  // 2 默认初始化 NG 除非下面有构造器代码块初始化
  final int AGE;
  final String NAME; // 下面代码块赋值了
  final int ID;
  // 3 默认初始化
  {
    NAME = "Robot";
  }
  // 4 构造器 【如果多个重载构造器，每一个都要初始化赋值的 一个都不能少！】
  public Person(){
    ID = 99;
  }
  // 5 构造器 带参数
  public Person(int id){
    ID = id;
  }
}
```

#### 方法里为什么不能初始化final变量？

常量的话，因为要在对象创造之前就要出生。但是方法是在对象之后出生的，不行。

#### 有什么final赋值的小技巧吗？

如果大家拥有的都是一样的数据，简单的不知。**就显示初始化。**

如果赋值不是一个简简单单的值，而是一个方法 `ID=method()` 那么就建议在代码块里

如果大家拥有的不一样，就可以带参数的构造器，例如上面的5。

#### final可以修饰参数吗？

```java
public void show(final int num){
  // 这里就是进去的常量
  // 不能进一步进行操作
  num += 10; // NG
}
```

#### final可以修饰局部变量吗（方法内的变量）？

可以的，只能用，不能修改赋值啥的。和上面的形参一样。

```java
public void show(final int num){
  final int NUM = 10;
}
```

final （属性 方法）+ static（属性 方法 代码块 内部类） = 可以修饰属性or方法

修饰属性就是 === **全局常量**（接口常用）

修饰方法 == 不能重写 不能继承

#### final经典题目

```java
public int addOne(final int x){
	return x++; // NG
	return ++x; // NG 
	return x + 1; // OK 没有改变x本身，只是加了
}
```

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Other o = new Other();
        new Main().addOne(o);
    }
    public void addOne(final Other o) {
        // o = new Other(); // NG 
        o.i++; // o是final不能修改 i又不是 OK的
        System.out.println(o.i);
    }
}

class Other {
    public int i;
}
```

### 8. 抽象类

#### 为什么有抽象类？

我个人感觉就是从具体到抽象吧。比如说猫猫狗狗都属于动物，动物和人都属于生物。生物和动物都是地球的物。。。就这么逐渐分啊分下去。会发现我们越来越抽象。就成了抽象类。

就像是一台电脑在画画的时候可能一个显示器一个正方形都能代替了。有点像抽象派画作的感觉。

#### abstract关键字可以修饰什么？

修饰Class Method

#### 抽象类需要注意什么？

- 不能实例化 → 不能new了 `abstract class Person{} NG new Person()`
- 一定要有构造器

#### 不能实例化了，为什么还有构造器？

虽然自己不用，但是继承的子类还需要用的。子类实例化的全过程。

所以抽象类一般都需要继承，由子类进行实例化。

#### 抽象方法需要注意什么？

抽象方法→没有方法体（因为是抽象的吉祥物性质的）

只有方法的声明 没有方法体。

```java
public void show(){} // 有大括号 所以有方法体
public abstract void show();
```

上面的show方法如果想要被调用的话，在没有static的情况下需要用对象来调用，但是抽象类怎么可以new呢。如何解决呢？

子类继承之后再说。

#### 为什么包含抽象方法的类一定是抽象类？

```java
// 首先这样写的问题是有抽象方法，类如果不是抽象的话。假设Person可以new 
// 但是这个new出来的对象在调用show的时候，show根本没有方法体。所以出错啊
class Person {
	String name;
	int age;
	public abstract void show();
}
```

**但是抽象类可以没有抽象方法。**

#### 抽象类的普通子类需要重写所有抽象方法吗？为什么 ？

结论，需要。

```java
abstract class Person {
	String name;
	int age;
	public abstract void show();
}
class Student extends Person {
	// 不重写的情况下。如果这个时候new Student 
 //  stu.show(); 根本也没有方法体。这样算什么呢。 所以必须重写。
}
```

#### 抽象类的抽象子类需要重写所有抽象方法吗？为什么 ？

不需要。

```java
abstract class Person {
	String name;
	int age;
	public abstract void show();
}
abstract class Student extends Person {
	// 不重写也没事 因为Studen也是抽象类 不会被new
}
```

子类重写了父类<u>所有的抽象方法</u>后，子类就可以实例化。

子类没有重写父类的<u>所有抽象方法</u>，子类就必须也是抽象类。abstract修饰。

这里的父类，既包括直接父类，也包括间接父类。

```java
abstract A {
		public abstract void A();
}
abstract B extends A {
  	// B类此时有俩抽象方法 A和B
		public abstract void B();
}
class C extends B {
  // 子类必须重写所有
  public void A();
  public void B();
}
```

#### 抽象类的运用场景是？

如果所有的子类都要重写你父类的某个方法。这个方法就可以是抽象的。

> 抽象类注意点。
>
> - 不能用来修饰属性，构造器
> - 不能用来修饰private（需要继承），final（需要继承），静态方法 static（原因，就是因为重写不能是静态static的）

#### 抽象类里一定要有抽象方法吗？

不一定，想有就有。但是抽象方法所属的类一定是抽象类。

其实多态的一个体现就是抽象类，如果没有多态，那么抽象类也不能new，那么抽象类还有什么意义。

**抽象类 抽象类对象（多态）= new 抽象类的子类**

#### 匿名对象PK匿名类

匿名对象就是这样的

```java
// Cat 假设是一个抽象类Animal的普通子类
public class Main {
    public static void main(String[] args) throws Exception {
        method(new Cat()); // 匿名对象
      	
      	Cat c = new Cat();
      	method(c);// 非匿名对象
    }
    public static void method(Animal animal){
        
    }
}
```

那么匿名类是啥意思呢。

类怎么可以匿名呢 其实这个类是子类，是默认继承了抽象类之后new的子类。

```java
public class Main {
    public static void main (String[] args) {
      // 作为一个抽象类怎么可以new
        Animal a = new Animal(){
          // 重写了所有方法这样写 就相当于新建了一个不知名的子类，并且new了这个子类
            @Override
            public void wow() {
                System.out.println("Something wow");
            };
        };
        method(a);
    }
    // 利用多态性，这里的参数就可以进去a
    public static void method(Animal animal){
        animal.wow(); // Something wow	
    }
}

abstract class Animal {
    String name;
    int age;
    public abstract void wow();
}

class Cat extends Animal {
    @Override
    public void wow() {
        System.out.println("Cat wow");
    }
}
```

下面在进一步，匿名子类匿名对象。

```java
public static void main (String[] args) {
        Animal a = new Animal(){
            @Override
            public void wow() {
                System.out.println("Something wow");
            };
        };
        method(a);
        method(new Animal(){
            @Override
            public void wow() {
                System.out.println("Something wowwowwow");
            };
        });
}
```

#### 为什么要使用匿名子类？

只过一次，为了省事。一次性，省事儿。不用浪费多余的空间来new一个对象。

#### 抽象类抽象方法实现一个应用：模板方法的设计模式

比如测试一个代码需要执行多长时间。

主要是利用 共通+ 变化

共通这种就在父类里写，变化就可以依靠子类来实现

```java
public class Main {
    public static void main (String[] args) {
       Template t = new SubTemp();
       //  SubTemp s = new SubTemp(); 这样也行 因为子类也有spendTime
       t.spendTime();
    }
}

abstract class Template {
    public void spendTime(){
        long start = System.currentTimeMillis();
        code(); // 这里写变化球 注意下面子类要重写具体的实现逻辑
        long end = System.currentTimeMillis();
        
        System.out.println("Spend time : " + (end - start));
    }
    public abstract void code();
}

class SubTemp extends Template{
    @Override
    public void code() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
```

在写一个差不多意思的。

```java
public class Main {
    public static void main (String[] args) {
       BankTemplateMethod btm = new DrawMoney();
       btm.process();
       System.out.println("******");
       BankTemplateMethod btm2 = new ManageFinancial();
       btm2.process();
    }
}

abstract class BankTemplateMethod {
    public void takeNumber(){
        System.out.println("takeNumber");
    }
    
    public abstract void transact(); // hook
    
    public void evalute(){
        System.out.println("evalute");
    }
    
    public final void process () {
        this.takeNumber();
        this.transact(); // 像个钩子，类似于js的回调函数。具体执行时，挂哪个子类，就执行哪个子类的实现代码 钩子就是即使我没调用，但是有这个动作or流程就执行了。
        this.evalute();
    }
}
class DrawMoney extends BankTemplateMethod {
    @Override
    public void transact() {
        System.out.println("I want to DrawMoney");
    }
}

class ManageFinancial extends BankTemplateMethod {
    @Override
    public void transact() {
        System.out.println("I want to ManageFinancial");
    }
}
```

### 9. 接口

interface 关键字

解决了 

- 多重实现 比如我想有多个爸爸的功能
- 功能的共通特征。比如一些类之间是没有一些直接关系的，比如鼠标键盘加湿器。但是这些又有统一的USB接口的话。那么这个USB接口用接口来定义是最好的。代表着一种规范。遵循了咱就是一伙儿的。
- 继承是【是不是】的关系。接口是【能不能】的关系。比如继承可以用`instanceof()`

#### 定义接口

接口类似于类的定义。成员根据jdk版本不同也会不同。

抽象类也不能new 但是有构造器。但是接口不能有构造器，这点和类不一样。抽象类的构造器是用来给子类用的。但是接口不是。

所以接口基本上通过类来实现来使用。记住是实现不是继承。

8之前就是 （**绝对不能有构造器 ，这点和类不一样**）

- 全局常量  `public static final`
- 抽象方法 `public abstract`

```java
interface Flyable {
    // 全局常量
    public static final  int MAX_SPEED = 7900;
    int MIN_SPEED = 1; // 不写就默认是上面的 

    // 抽象方法
    public abstract void fly();
    void stop(); // 不写就默认是上面的 抽象方法没有方法体
}
```

8之后就是 新增 +

- 静态方法
- 默认方法

注意点

- 不能有构造器
- 通过类来去实现，实现了所有的抽象方法。就是普通类可以实例化。
- 没有实现所有的抽象方法，那么这个类就是抽象类。
- 抽象的方法一般叫实现，而不是重写。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Plane.MAX_SPEED);
        System.out.println(Plane.MIN_SPEED);
        Plane p = new Plane();
        p.fly();
        p.stop();
    }
}

interface Flyable {
    // 全局常量
    public static final  int MAX_SPEED = 7900;
    int MIN_SPEED = 1; // 不写就默认是上面的 
    // 抽象方法
    public abstract void fly();
    void stop(); // 不写就默认是上面的 抽象方法没有方法体
}
// 实现了全部方法 所以是普通类
class Plane implements Flyable {
    // 虽然这里写的是override 其实严格意义上这属于实现而不是重写
    @Override
    public void fly() {
        System.out.println("Plane fly");
    }
    @Override
    public void stop() {
        System.out.println("Plane stop");
        
    }
}
// 没有实现全部方法 抽象类OK
abstract class Hae implements Flyable {
    @Override
    public void fly() {
        System.out.println("Hae fly");
    }    
}
```

#### 多接口实现和接口继承

- 接口可以多重实现。

`class A extends BB implements CC,DD,EE{}`

- 接口之间可以继承，且多继承。

```java
 interface AA {
    void a();
}
interface BB {
    void b();
}
interface CC extends AA, BB {
    // 这时候CC就有了AA和BB的所有抽象方法
    // 接下来实现CC的时候 AA和BB 的所有抽象方法也要实现
}
```

- 接口具有多态性特点
- 接口实际上就是一种规范

#### 接口实现的一个案例

- 接口一般都要多态实现
- 意思就是执行调用的都是实现类的方法

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Computer c = new Computer();
        Flash f = new Flash(); // 多态实现，接口又不能new
        c.transferData(f);
    }
}

class Computer {
    // 这里很重要 传入的是一个规范，意思就是说下面要遵循
    public void transferData(USB usb) {
       // 这里都是调用 实现了USB接口的具体实现类的方法
        usb.start();
        System.out.println("transefering......");
        usb.stop();
    }
}

interface USB {
    void start();
    void stop();
}

class Flash implements USB {
    @Override
    public void start() {
        System.out.println("Flash start");
    }
    @Override
    public void stop() {
        System.out.println("Flash stop");
    }
}

class Keyboard implements USB {
    @Override
    public void start() {
        System.out.println("Keyboard start");
    }
    @Override
    public void stop() {
        System.out.println("Keyboard stop");
    }
}
```

#### 接口里面的匿名实现是什么？

接上面的代码例子。配合之前抽象类的时候也说了匿名对象和匿名子类问题。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Computer c = new Computer();
        
        // 1 非匿名类 非匿名对象
        Flash f = new Flash();
        c.transferData(f);
        
        // 2 非匿名类 匿名对象
        c.transferData(new Keyboard());
        
        // 3 匿名类 非匿名对象
        USB phone = new USB(){
            @Override
            public void start() {
                System.out.println("phone start");
            }
            @Override
            public void stop() {
                System.out.println("phone stop");
            }
        };
        c.transferData(phone);
        
        // 4 匿名类 匿名对象
        c.transferData(new USB(){
            @Override
            public void start() {
                System.out.println("iphone start");
            }
            @Override
            public void stop() {
                System.out.println("iphone stop");
            }
        });
    }
}
```

#### 面向接口编程的代理模式是什么？

一些简单的应用场景

- **安全代理** 屏幕对真实角色的直接访问
- **远程代理** 通过对代理类处理远程方法调用 RMI
- **延迟加载** 当有大的图片的时候先加载一个代理的小的，不需要全部图片打开。当需要查看大的时候在使用。
- **静态代理** 代码在编写的时候就可以确定是什么行为。静态定义代理类。
- **动态代理** 代码在运行时候确定代理行为。动态生成代理类。（反射，动态代理）

写一个例子吧。就是代理上网的例子。Proxy代表代理服务器。Server 代表服务器。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Server s = new Server();
        ProxyServer p = new ProxyServer(s);
        p.browse();
        // 如果不是被代理的模式，还需要直接写
        // server.browse();
    }
}

// 定义一个接口，用来定义规范
interface Network {
    void browse();
}
// 被代理类，不直接参与
// 但是因为实现了Network接口，所以要实现所有方法
class Server implements Network {
    @Override
    public void browse() {
        System.out.println("Server browse....");
    }
}
// 代理类
// 定义一个私有接口对象
// 构造器里要初始化 接口对象的属性
// 添加自己的方法 check
// 重写接口
class ProxyServer implements Network {

    private Network work; // 多态体现

    public ProxyServer (Network work){
        this.work = work;
    }

    public void check () {
        System.out.println("ProxyServer checking...");
    }

    @Override
    public void browse() {
        check();
       // 这里调用的还是实例化的真实的被代理类（此时，被代理类作为参数在构造器里初始化了）
        work.browse();
    }
}
```

在写一个明星和经纪人的代理关系

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Manager m = new Manager(new SuperStar());
        m.doAll();
    }
}

interface Star {
    void confer();
    void signContract();
    void bookTicket();
    void sing();
    void collectMoney();
}

class SuperStar implements Star {
    @Override
    public void confer() {
        
    }
    @Override
    public void signContract() {
        
    }
    @Override
    public void bookTicket() {
        
    }
    @Override
    public void sing() {
        String title = getTitle();
        System.out.println("我要真唱 ♪♪♪♪ " + title);
    }
    private String getTitle(){
        return "super star";
    }
    @Override
    public void collectMoney() {
        
    }
}

class Manager implements Star {
    private Star star;
    public Manager(Star star) {
        this.star = star;
    }

    public void check() {
        System.out.println("Manager checking...");
    }
    @Override
    public void confer() {
        System.out.println("Manager confer...");
    }
    @Override
    public void signContract() {
        System.out.println("Manager signContract...");
    }
    @Override
    public void bookTicket() {
        System.out.println("Manager bookTicket...");
    }
    @Override
    public void sing() {
        check();
        // 执行的是巨星
        star.sing();
    }

    @Override
    public void collectMoney() {
        System.out.println("Manager collectMoney...");
    }
    
    public void doAll(){
        confer();
        signContract();
        bookTicket();
        sing();
        collectMoney();
    }
}
```

#### 工厂模式是什么呢？

这里只是工厂的初步概念。目的就是为了解耦合还有一些理念上的学习。并不是完全的工厂模式

**为了理解工厂模式，先看一个没有工厂的情况下。**

```java
// 没有工厂模式
public class Main {
    public static void main(String[] args) throws Exception {
      // 实例化对象和方法调用一起在一个函数执行，不好。
        Tesla t = new Tesla();
        Nio n = new Nio();
        t.run();
        n.run();
    }
}
interface Car {
    void run();
}
class Tesla implements Car {
    @Override
    public void run() {
        System.out.println("Tesla run....");
    }
}

class Nio implements Car {
    @Override
    public void run() {
        System.out.println("Nio run....");
    }
}

// 简单工厂模式
public class Main {
    public static void main(String[] args) throws Exception {
      // 略微工厂感觉的了
        Car t = CarFactory.getTesla();
        Car n = CarFactory.getNio();
        t.run();
        n.run();
        
    }
}

interface Car {
    void run();
}
class Tesla implements Car {
    @Override
    public void run() {
        System.out.println("Tesla run....");
    }
}

class Nio implements Car {
    @Override
    public void run() {
        System.out.println("Nio run....");
    }
}

class CarFactory {
    public static Car getTesla(){
        return new Tesla();
    }
    public static Car getNio(){
        return new Nio();
    }
}

// 增加产品那么CarFactory也要改 不好 违反开闭原则（扩展开放 修改封闭）
// 如果需要增加一个品牌车的话 那么就直接新增一个车 新增一个工厂 无需改动现有的Tesla和Nio了

public class Main {
    public static void main(String[] args) throws Exception {
        Car t = new TeslaFac().getCar();
        Car n = new NioFac().getCar();
        t.run();
        n.run();
        
    }
}

interface Car {
    void run();
}
class Tesla implements Car {
    @Override
    public void run() {
        System.out.println("Tesla run....");
    }
}

class Nio implements Car {
    @Override
    public void run() {
        System.out.println("Nio run....");
    }
}

interface Factory{
    Car getCar();
}

class TeslaFac implements Factory{
    public Tesla getCar(){
        return new Tesla();
    }
}
class NioFac implements Factory{
    public Nio getCar(){
        return new Nio();
    }
}
```

#### 父类和接口变量重名怎么办？

```java
interface A {
    int x = 1;
    int yA = 100
}

class B {
    int x = 2;
    int yB = 200;
}

class C extends B implements A {
    public void px(){
        // 编译不通过 x不明确
      	// 这样并不可以，因为没有指名到底是什么。也没有就近原则了。
        // System.out.println(x);// NG
	      System.out.println(A.x);
        System.out.println(super.x);
      
    }
    public static void main(String[] args){
        new C().px();
    }
}

interface Playable {
    void play();
}

interface Bounceable {
    void play();
}
interface Rollable extends Playable, Bounceable {
    Ball ball = new Ball("Ping Pang");
}

class Ball implements Rollable {

    private String name;
    public String getName(){
        return name;
    }
    public Ball(String name) {
        this.name = name
    }

    @Override
    public void play() {
        // 接口内的属性都是 public static final 不能重写 不能继承
        // ball = new Ball("jdehiewh"); // The final field Rollable.ball cannot be assigned
        System.out.println(ball.getName());
    }
}
```

#### JDK8后新特性

+ 静态方法 实现类or子类是拿不到的 接口定义的静态方法只能静态调用

接口.静态方法，这样就很像工具类的实现方式。（其实现在接口越来越像类了）

+ 默认方法 有方法体。

实现类的对象可以直接调用默认方法，并且实现类可以重写这个默认方法

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Foo f = new Foo();
        // Foo.methodA();接口定义的静态方法只能静态调用
        f.methodB();
        f.methodC();
    }
}

interface A {
    // 静态方法 → 实现类or子类是拿不到的 接口定义的静态方法只能静态调用
    public static void methodA(){
        System.out.println("interface A static methodA");
    } 
    // 默认方法
    public default void methodB(){
        System.out.println("interface A default methodB");
    }
    // 不写也就是省略了public 只能是public
    default void methodC(){
        System.out.println("interface A default methodC");
    }
}

class Foo implements A {
    // 虽然这里什么都没有
	  // 默认方法直接ok 静态方法不行
}
```

和属性不同，实现继承都存在同名方法的情况下。方法默认执行父类的，而不是实现类。**类优先原则。**

#### 那如果多重继承的接口里有同名的方法呢？

会有冲突 必须要重写。

```java
public class Main {
    public static void main(String[] args) throws Exception {
       D d = new D();
       d.myMethod();
        
    }
}

interface AA {
    default void method(){
        System.out.println("AA method");
    }
}
interface BB {
    default void method(){
        System.out.println("BB method");
    }
}

class CC {
    public void method(){
        System.out.println("CC method");
    }
}
class D extends CC implements AA, BB {
    public void method(){
        System.out.println("C method");
    }
   public void myMethod(){
        method(); // 调用自己定义的重写方法
        super.method(); // 调用父类中声明的重写方法
        // 调用接口中声明的默认方法
        AA.super.method(); // 规定写法
        BB.super.method();
   }
}
```

关于jdk8新特性带来的问题就是平常接口的方法是没有方法体的，但是新增的静态方法和默认方法是有方法体的，这就意味着其实在实现的过程中没必要在进行重写了。这样的话其实就很像普通类了。以后看到默认方法就是可以说实现类可以直接调用。这就足够了。

### 10 .内部类

#### 内部类为什么要有？

在一些场景下。类的属性无法完整描述一个属性的时候，就可以用内部类来定义。比如人有大脑，这个大脑简单的只是一个属性不太够，而且没必要把大脑写在外部供其他类使用。那么就可以写在内部。

分类

- 内部类。外部类。类A可以声明在类B的里面。那么类A就是内部类，类B就是外部类。
- 成员内部类（静态 非静态）。局部内部类（方法内，代码块内，构造器内定义）。

写一个很全很全的内部类。

```java
class Person {
    String name = "Amy";
    int age = 29;

    public void eat() {
        System.out.println("Person eat");
    }

    /******************** 成员内部类 **********************/
    // static成员内部类
    static class Heart {
        String name;
        int age;

        public void beat() {
            System.out.println("Heart beat");
            // eat(); NG static无法调用非静态方法 因为生命周期加载顺序不一样
        }
    }

    // 非static成员内部类
    class Leg {
        String name = "Big Leg";

        public Leg() {

        }

        public void kick() {
            System.out.println("Leg Kick");
            Person.this.eat(); // 外部类的非静态属性
            eat();// 这是简写 注意是Person.this 只是单纯的this.name 说的就是leg了
            System.out.println(age);
        }

        public void display(String name) {
            System.out.println(name); // 方法的形参
            System.out.println(this.name);// 内部类的属性
            System.out.println(Person.this.name); // 外部类的属性
        }
    }

    /******************** 局部内部类 **********************/
    // 方法内
    public void method() {
        class AA {

        }
    }

    // 代码块内
    {
        class BB {

        }
    }

    // 构造器内
    public Person() {
        class CC {

        }
    }

} 
```

#### 如何实例化成员内部类的对象（静态static）?

多了一层调用 **外部类.内部类 对象 = new 外部类.内部类**

```java
public static void main(String[] args) throws Exception {
        Person.Heart pHeart = new Person.Heart();
        pHeart.beat(); // Heart beat
}
```

#### 如何实例化成员内部类的对象（非静态）？

外部类普通new一个外部类对象

**外部类.内部类 内部类对象 = 外部类对象.new 内部类**

```java
Person p = new Person();
Person.Leg pLeg = p.new Leg();
pLeg.kick();
pLeg.display("thin");
```

#### 如何实例化局部内部类？

开发中在代码块和构造器实现的都比较少。甚至在方法内实现的都比较少。

但是最常用的是在方法内部，返回了某一个实现了某接口的对象。

也就是方法内部走一圈过后，生成了一个实现接口的对象。

```java
public class InnerClassTest1 {

    // 很少见 局部内部类
    public void innerMethod() {
        class AAA{
        }
    }


    // 基本上开发都是返回一个实现的接口对象
    // 比如这一个就是实现了 Comparable 接口类的对象

    public Comparable getComparable() {
        // 方式1 非匿名 实现类有名 匿名对象
        // class MyComparable implements Comparable {
        //     @Override
        //     public int compareTo(Object o) {
        //         return 0;
        //     }
        // }
        // return new MyComparable();


        // 方式2 匿名 实现类和对象都没名
        return new Comparable(){

          @Override
          public int compareTo(Object o) {
            return 0;
          }

        };
    }
}
```

#### 局部内部类的变量为什么必须是final ?

规定。外部类和内部类事实上生成 了2个文件。对应了2个类。 

按理说methodA内部的变量应该是只在methodA里面的，但是由于是一个内部类。所以在另外的结构里 也就是BB 也有可能使用到这个结构。必须声明成final

jdk7之前必须显式声明成final，7之后就无所谓了默认就是了。

```java
class AA {
    
    ....
    
    public void methodA(){
        // 必须是final
        int num = 10; // final 在局部内部类的方法 如果调用局部内部类所在的方法中的局部变量
        class BB {
            public void methodB {
                num = 99; // final 
                System.out.println(num);
            }
        }
    }
}
```

### 11. 枚举类

- 类的对象有限 + 确定
- 定义一组常量，使用枚举类。
- 如果枚举类只有1个对象，因为还是常量。则就是单例模式实现方式了。
- jdk5之后来的

比如 周一到周日，性别，季节，订单状态。

#### 如何定义枚举类？

**1 jdk5之前 自定义枚举类**

这里为什么要使用构造器来初始化的原因是因为，代码块还有显式赋值都是无法根据参数不同而定义的，是死的，而构造器可以根据参数不同而进行调整，是活的。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Season spring = Season.SPRING;
        System.out.println(spring);
    }
}

class Season {
    // ① 声明Season对象的属性 使用 private final进行修饰 既不能被随意修改，也不能被继承。如果不是私有，那么外部就可以随便new 那就不是确定的了
    private final String sName;
    private final String sDesc;
    // ② 私有化类的构造器，并给对象属性赋值。
    private Season(String sName, String sDesc){
        this.sName = sName;
        this.sDesc = sDesc;
    }
    // ③ 提供当前枚举类的对个对象 public static final 不可以改，可以使用static直接调用
    public static final Season SPRING = new Season("春", "春暖花开");
    public static final Season SUMMER = new Season("夏", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋", "秋高气爽");
    public static final Season WINTER = new Season("冬", "冰冷刺骨");
    // ④ 其他诉求1 获取枚举类对象的属性（既然private不给获取，那就写方法进行获取）
    public String getSName(){
        return this.sName;
    }
    public String getSDesc(){
        return this.sDesc;
    }
    @Override
    public String toString() {
        return "Season{" + "seasonName='" + sName + '\'' + ", seasonDesc='" + sDesc + '\'' + '}';
    }
    
}
```

2 jdk5之后 使用关键字 **enum** 定义。

这里直接输出无需重写toString的原因是因为 enum这个类是重写了。

这里的Enum继承的不是 Object 类，继承的是 java.lang.Enum Enum的类

而 toString方法是被重写了的 正常的 Object 类打印出来的是地址

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Season spring = Season.SPRING;
        System.out.println(spring);
    }
}

enum Season {
    
    // 自定义的时候重复的代码提出掉，提供当前枚举类的对象。多个对象之间用,隔开，最后；
    SPRING("春", "春暖花开"),
    SUMMER("夏", "夏日炎炎"),
    AUTUMN("秋", "秋高气爽"),
    WINTER("冬", "冰冷刺骨");
    
    private final String sName;
    private final String sDesc;
    
    private Season(String sName, String sDesc){
        this.sName = sName;
        this.sDesc = sDesc;
    }
    public String getSName(){
        return this.sName;
    }
    public String getSDesc(){
        return this.sDesc;
    }
  
  	// 一般不去重写 public String toString() {}
    
}
```

一段超级简单的替换代码

```java
class Status {
    private final String NAME;
    private Status(String name) {
        this.NAME = name;
    }
    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    public String getNAME(){
        return NAME;
    }
    @Override
    public String toString() {
        return NAME;
    }
}

// 替换之后

enum Status {
    FREE,
    BUSY,
    VOCATION
}
```

#### 枚举类的常用方法是？

要注意就是Enum 实现的枚举类可以使用下面的方法，非Enum实现的不可以实现。

这篇文章可供参考 

https://www.cnblogs.com/ziph/p/13068923.html

| 返回值   | 方法                                    | 描述                                        |
| :------- | :-------------------------------------- | :------------------------------------------ |
| String   | name()                                  | 获取枚举成员的名称                          |
| static T | valueOf(Class<T> enumType, String name) | 获取指定枚举成员名称和类型的枚举成员        |
| String[] | values()                                | 获取枚举成员的所有值                        |
| int      | compareTo(E o)                          | 比较此枚举与指定对象的顺序                  |
| int      | hashCode()                              | 获取枚举成员的哈希值                        |
| int      | ordinal()                               | 获取枚举成员的序数（第一个枚举成员位置为0） |
| String   | toString()                              | 返回枚举成员名称                            |
| Class<E> | getDeclaringClass()                     | 获取枚举成员的类对象                        |

#### name和toString有什么区别？

关于name方法和toString方法，其实很简单。name()就是根据枚举成员来获取该枚举成员的字符串名称。而同String方法也是用来获取枚举成员的字符串名称。虽然作用都是相同的，但是name方法是用final修饰的不能被重写，而toString是可以被重写的。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Season.SPRING.toString());
        System.out.println(Season.SPRING.name());
    }
    
    enum Season {
        SPRING("春", "春暖花开"),
        SUMMER("夏", "夏日炎炎"),
        AUTUMN("秋", "秋高气爽"),
        WINTER("冬", "冰冷刺骨");
        private final String sName;
        private final String sDesc;
    
        private Season(String sName, String sDesc) {
            this.sName = sName;
            this.sDesc = sDesc;
        }
        public String getSname(){
            return this.sName;
        }
        public String getSdesc(){
            return this.sDesc;
        }
        @Override
        public String toString() {
            return "Season{" + "seasonName='" + getSname() + '\'' + ", seasonDesc='" + getSdesc() + '\'' + '}';
        }
    }
}
```

#### `valueOf()`用法是什么？

此方法的作用是**传入一个字符串，然后将它转换成对应的枚举成员**。这里传入的字符串必须与定义的枚举成员的名称一致，严格区分大小写。如果传入的字符串并没有找到其对应的枚举成员对象，就会抛出 *java.lang.IllegalArgumentException* 异常

```java
public static void main(String[] args) throws Exception {
        Season w = Season.valueOf("WINTER");
        System.out.println(w); // Season{seasonName='冬', seasonDesc='冰冷刺骨'}
        
        Season s = Season.valueOf("SPR");
        System.out.println(s); // java.lang.IllegalArgumentException 
}
```

`ordinal()`用法

方法是获取枚举类的序列数，意思就是初始是0，然后按照顺序。

```java
// 获取指定枚举成员的次序
int i = Season.AUTUMN.ordinal();
System.out.println(i); // 2
// 获取所有成员的次序
Season[] seasons = Season.values();
        for (Season s: seasons){
            System.out.println(s + " ->" + s.ordinal());
}
Season{seasonName='春', seasonDesc='春暖花开'} ->0
Season{seasonName='夏', seasonDesc='夏日炎炎'} ->1
Season{seasonName='秋', seasonDesc='秋高气爽'} ->2
Season{seasonName='冬', seasonDesc='冰冷刺骨'} ->3
```

`compareTo()`

**compareTo方法比较的是两个枚举成员的次序数，并返回次序相减后的结果。**

```java
// 源码
public final int compareTo(E o) {
        Enum<?> other = (Enum<?>)o;
        Enum<E> self = this;
        if (self.getClass() != other.getClass() && // optimization
            self.getDeclaringClass() != other.getDeclaringClass())
            throw new ClassCastException();
        return self.ordinal - other.ordinal;
}
```

`values()`

是**编译器自动生成**的方法，Enum中并没有该方法，返回包括所有枚举变量的数组。编译自动生成意思就是源码的时候是查找不到。

```java
Season[] s = Season.values(); 
        for (int i = 0;i < s.length ;i++ ){
            System.out.println(s[i]);
} 
```

#### 枚举类实现接口

枚举类也可以实现接口。

```java
// 1 实现接口
public class Main {
    public static void main(String[] args) throws Exception {
        Season a = Season.AUTUMN;
        a.show(); //  Seasons
    }
}
interface Info{
    void show();
}

enum Season implements Info {
    SPRING("春", "春暖花开"),
    SUMMER("夏", "夏日炎炎"),
    AUTUMN("秋", "秋高气爽"),
    WINTER("冬", "冰冷刺骨");
    private final String sName;
    private final String sDesc;


    private Season(String sName, String sDesc) {
        this.sName = sName;
        this.sDesc = sDesc;
    }

    public String getSname(){
        return this.sName;
    }
    public String getSdesc(){
        return this.sDesc;
    }

    @Override
    public String toString() {
        return "Season{" + "seasonName='" + getSname() + '\'' + ", seasonDesc='" + getSdesc() + '\'' + '}';
    }
    @Override
    public void show() {
        System.out.println("Seasons");
    }
}
```

进一步来对每一个对象来重写`show()`,这样每一个对象调用的都是不同的内容。

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Season[] seasons = Season.values();
        for (int i = 0; i < seasons.length; i++ ){
            seasons[i].show();
        } 
    }
  	// Spring
    // Summer
    // Autumn
    // Winter
}

interface Info{
    void show();
}

enum Season implements Info {
    SPRING("春", "春暖花开"){
        @Override
        public void show() {
            System.out.println("Spring");
        }
    },
    SUMMER("夏", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("Summer");
        }
    },
    AUTUMN("秋", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("Autumn");
        }
    },
    WINTER("冬", "冰冷刺骨"){
        @Override
        public void show() {
            System.out.println("Winter");
        }
    };
    private final String sName;
    private final String sDesc;


    private Season(String sName, String sDesc) {
        this.sName = sName;
        this.sDesc = sDesc;
    }

    public String getSname(){
        return this.sName;
    }
    public String getSdesc(){
        return this.sDesc;
    }

    @Override
    public String toString() {
        return "Season{" + "seasonName='" + getSname() + '\'' + ", seasonDesc='" + getSdesc() + '\'' + '}';
    }
}
```

### 12. 注解

关于注解是什么的问题。

@Annotation 个人感觉很像Python的@装饰器。但只是像而已。就是在不修改元代码的前提下增加以下功能。

```
从 JDK 5.0 开始, Java 增加了对元数据(MetaData) 的支持, 也就是
Annotation(注解)
Annotation 其实就是代码里的特殊标记, 这些标记可以在编译, 类加 载, 运行时被读取, 并执行相应的处理。通过使用 Annotation, 程序员 可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。代 码分析工具、开发工具和部署工具可以通过这些补充信息进行验证 或者进行部署。
Annotation 可以像修饰符一样被使用, 可用于修饰包,类, 构造器, 方法, 成员变量, 参数, 局部变量的声明, 这些信息被保存在 Annotation 的 “name=value” 对中

框架 = 注解 + 反射 + 设计模式。
```

以我现在的能力，我感觉注解就是一个在框架内很有用的东西。

@Override: 限定重写父类方法, 该注解只能用于方法

@Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为 所修饰的结构危险或存在更好的选择

@SuppressWarnings: 抑制编译器警告

### 13 反射

这个以我目前的水平。感觉反射就是一面镜子。本来是类→对象这样一个流程。反射就是倒推的干感觉。可以利用对象来推断出运行中的类。

用来描述类的类。就是大的 `Class`

javac 命令进行编译 会生成一个or多个字节码文件(.class结尾)。`Main.java → Main.class`

java 对字节码文件进行解释运行，就是把字节码文件加载到内存里。`Main.class → 加载到内存` 这个过程就是类的加载。加载到内存里的类运行的类，就叫做运行类。这个加载进来的类，**Class 就是 一个实例。**<u>Class clazz = Person.class;本质就是类也是对象，是大的Class对象。</u>也是万物皆对象的体现。

#### 使用反射和不使用反射区别在于？

使用反射之前的Person实例操作

```java
// 1.新建实例对象
Person p1 = new Person("Tom", 12);
// 2.通过对象调用内部的方法和属性
p1.age = 10;
System.out.println(p1.toString());
p1.show();
// 在Person类外部，不可以调用内部私有结构
// p1.name = "Amy";
// p1.showNation();
```

使用反射之后的Person实例操作

```java
public static void main(String[] args) throws Exception {
        Class clazz = Person.class;
        Constructor cons = clazz.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Jerry", 99); // 这里是一个Person 对象 需要显式看到的话可以强转
  			Person p = (Person) obj;
        System.out.println(obj); // Person{name='Jerry', age=99}
  
  			// 2. 调用对象指定的属性
        Field age = clazz.getDeclaredField("age");
        age.set(p, 100);
        System.out.println(p.toString()); // Person{name='Jerry', age=100}

        // 3.调用方法
        Method show = clazz.getDeclaredMethod("show"); // 这里使用的是空参的show 如果需要有参数的继续看getDeclaredMethod
  
        // 调用函数的时候没有参数就可以不用
        // show.invoke(obj, args) 有参数就用参数
        show.invoke(p);
}

```

#### 通过反射可以调用private结构吗？

可以的。

比如新建一个**私有构造器**。

```java
private Person(String name) {
        this.name = name;
}

Class clazz = Person.class;
Constructor con = clazz.getDeclaredConstructor(String.class);
con.setAccessible(true);
Object p = con.newInstance("TOMMY");
System.out.println(p); // Person{name='TOMMY', age=0}
```

比如**私有属性**

```java
Class clazz = Person.class;
Constructor con = clazz.getDeclaredConstructor(String.class);
con.setAccessible(true);
Object p = con.newInstance("TOMMY");

// 属性名
Field name = clazz.getDeclaredField("name");
name.setAccessible(true);
name.set(p, "Amy");
System.out.println(p); // Person{name='Amy', age=0}
```

比如**私有方法**

```java
Class clazz = Person.class;
Constructor con = clazz.getDeclaredConstructor(String.class);
con.setAccessible(true);
Object p = con.newInstance("TOMMY");

Method showNation = clazz.getDeclaredMethod("showNation", String.class);
showNation.setAccessible(true);
// 相当于 String name = p.showNation("China")
// 原来是对象调用方法，现在是方法需要对象支援
showNation.invoke(p, "China"); // // 我的国籍是：China


// 如果接收 return 返回值的话
String nation = (String)showNation.invoke(p, "CHINAA"); // 这里需要强转的原因是返回的类型是Object
System.out.println(nation); // CHINAA
```

#### 通过反射会破坏单例模式吗？什么时候使用反射什么时候面向对象？

反射机制会与面向对象封装性有一定冲突和矛盾？

封装性私有的方法 是内部使用方法，在内部调用过了。在**外部其实也用不着**，包括单例模式。即使反射可以调用内部私有对象，但没必要。可以，但没必要。

<u>封装性只是建议你。反射是表示能不能。</u>

直接new 对象是常态，反射只是在运行时，编译的时候没有确定，运行时候可以确定类的时候用反射。

