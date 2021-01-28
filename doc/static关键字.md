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

## static关键字

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

### 静态变量的好处？

直接调用，无需新建对象。比如 `Math.PI` 多用于工具类啥的。

### 静态方法是？

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

### 开发中如何确定属性or方法是静态的？

多个对象大家一起共享的一般是静态属性。

如果操作静态属性的一般就是静态方法。

或者是工具类的种种一般都是静态的。`Math,Arrays,Collection`

### 写一个简单的static应用（制造圆类）

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

### 什么是单例模式？

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

### 为什么要用单例模式啊？

为了减少系统性能开销。比如读取一些配置资源，其实一份就够了永久贮存在内存里就够了。没必要多份。

### 关于Main函数

main也是静态方法 所以无法调用非静态属性pr方法

也是入口函数
