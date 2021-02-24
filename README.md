# 设计模式(Desing Patterns)

## 写在前面的

设计模式感觉蛮有用的，具体代码写逻辑的时候感觉算法有用。在实际上写一个项目的时候感觉设计模式有用。

## 七大原则

### 单一职责原则(Single Responsibility Principle-SRP)

简单的就是 **一个类一个职责**，一个职责并不代表就写一个方法一个代表，偏抽象的职责。

单一不仅仅是类还有可能是方法。反正模块化也不是*Java*独有的，其他编程都一样。

至于这个原则的好处还用问吗，问就是<u>低耦合高内聚</u>！

**进化前**

根本不是单一职责

```java
package com.design.princple;

/**
 * 单一职责 方案A
 */
public class SingleResponsibilityA {
    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        // 这样不行的，这么多交通工具是要拆分的
        // 毕竟单一职责，一个方法怎么可以干这么多
        v.run("Plane");
        v.run("Tesla");
        v.run("Train");
    }
}

/**
 * 假设写一个交通工具类 有飞机，火车，汽车，走路
 * 下面就不行了，因为你发现只能在公路跑
 */
class Vehicle {
    public void run(String vehicle){
        System.out.println(vehicle + " Road run....");
    }
}

```

**二级进化**

实现类级别的分解

```java
package com.design.princple;

/**
 * 单一职责 方案B
 */
public class SingleResponsibilityB {
    public static void main(String[] args) {

        RoadVehicle rv = new RoadVehicle();
        AirVehicle av = new AirVehicle();
        SeaVehicle sv = new SeaVehicle();
        rv.run("Gaotie");
        av.run("ANA");
        sv.run("Hexie");
    }
}

/**
 * 遵守了单一职责原则 
 * 该谁的活儿哪个类就去干 → 分解类
 */
class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " RoadVehicle run....");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " AirVehicle run....");
    }
}

class SeaVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " SeaVehicle run....");
    }
}

```

**三级进化**

实现方法级别的分解

```java
package com.design.princple;

/**
 * 单一职责 方案C
 */
public class SingleResponsibilityC {
    public static void main(String[] args) {

        RoadVehicle rv = new RoadVehicle();
        AirVehicle av = new AirVehicle();
        SeaVehicle sv = new SeaVehicle();
        rv.run("Gaotie");
        av.run("ANA");
        sv.run("Hexie");
    }
}

/**
 * 遵守了单一职责原则 
 * 比起 SingleResponsibilityB 并没有在类上面遵循单一职责
 * 但是在方法的级别上还是遵循了单一职责
 */
class Vehicle2 {

    public void run(String vehicle){
        System.out.println(vehicle + " Road run....");
    }
    public void runAir(String vehicle){
        System.out.println(vehicle + " Air run....");
    }
    public void runSea(String vehicle){
        System.out.println(vehicle + " Sea run....");
    }
}
```

### 接口隔离原则（Interface Segregation Principle）

客户端不应该依赖不需要的接口，即一个类对另一个类的依赖应该在最小的接口上。

本质上就是说，如果一个接口有10个方法，但是你只用的上其中的5个。那么不是应该全部实现10个。而是应该去分解这个10个接口。

**进化前**

```java
interface SegregationA1 {
    void seA1();
    void seA2();
    void seA3();
    void seA4();
    void seA5();
}

class SegregationAB implements SegregationA1 {

    @Override
    public void seA1() {
       System.out.println("SegregationAB seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationAB seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationAB seA3");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationAB seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationAB seA5");
    }
}

class SegregationAC implements SegregationA1 {

    @Override
    public void seA1() {
       System.out.println("SegregationAD seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationAD seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationAD seA3");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationAD seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationAD seA5");
    }
}

// SegregationAD 通过接口 SegregationA1 去（依赖）使用 SegregationAB
// 但是只会用A1 A2 A3
// 这样的结果就是其实 SegregationAB 的 4和5白写了
class SegregationAD {
    public void depend1(SegregationA1 i) {
        i.seA1();
    }
    public void depend2(SegregationA1 i) {
        i.seA2();
    }
    public void depend3(SegregationA1 i) {
        i.seA3();
    }
}

// SegregationAE 通过接口 SegregationA1 去（依赖）使用 SegregationAC
// 但是只会用A4 A5
// 这样的结果就是其实 SegregationAC 的 2，3白写了
class SegregationAE {
   	public void depend1(SegregationA1 i) {
        i.seA1();
    }
    public void depend4(SegregationA1 i) {
        i.seA4();
    }
    public void depend5(SegregationA1 i) {
        i.seA5();
    }
}
```

**进化后**

实际上就是把上面的*SegregationA1*拆分成3份 需要什么实现什么 然后通过接口进行连接起来

```java
public class InterfaceSegregationB {
    public static void main(String[] args) {

        BBA bba = new BBA();
        // BBA 通过 接口 去依赖 BA
        bba.depend1(new SegregationBA()); // SegregationBA seA1
        bba.depend2(new SegregationBA()); // SegregationBA seA2
        bba.depend3(new SegregationBA()); // SegregationBA seA3

        BBB bbb = new BBB();
        // BBB 通过 接口 去依赖 BB
        bbb.depend1(new SegregationBB()); // SegregationBB seA1
        bbb.depend4(new SegregationBB()); // SegregationBB seA4
        bbb.depend5(new SegregationBB()); // SegregationBB seA5
    }
}

// 根据具体需求把上面进化前的接口拆分成了3份
interface SegregationB1 {
    void seA1();
}

interface SegregationB2 {
    void seA2();

    void seA3();
}

interface SegregationB3 {
    void seA4();

    void seA5();
}

// 分别进行实现 需要什么实现什么
class SegregationBA implements SegregationB1, SegregationB2 {

    @Override
    public void seA1() {
        System.out.println("SegregationBA seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationBA seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationBA seA3");
    }

}

// 分别进行实现 需要什么实现什么
class SegregationBB implements SegregationB1, SegregationB3 {

    @Override
    public void seA1() {
        System.out.println("SegregationBB seA1");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationBB seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationBB seA5");
    }

}

class BBA {
    // 这里就是放进去需要实现的接口
    public void depend1(SegregationB1 i) {
        i.seA1();
    }

    public void depend2(SegregationB2 i) {
        i.seA2();
    }

    public void depend3(SegregationB2 i) {
        i.seA3();
    }
}

class BBB {
    public void depend1(SegregationB1 i) {
        i.seA1();
    }

    public void depend4(SegregationB3 i) {
        i.seA4();
    }

    public void depend5(SegregationB3 i) {
        i.seA5();
    }
}
```

### 依赖倒转原则（Dependence Inversion Principle）

- 高层模块不应该依赖底层模块，相反，底层模块应该依赖抽象高层模块
- 抽象不应该依赖细节。
- 中心思想，面向接口编程。
- 其实本质就是抽象思想。。各种具体的全部抽象起来，全部规范起来。用接口去完成。

现在写一个例子看一下这个原则

写一个Person接收信息的例子

**进化前**

```java
public class DependenceInversionA {
    public static void main(String[] args) {
        PersonA p = new PersonA();
        p.recieve(new EmailA()); // Email: Hello World
    }
}

class EmailA {
    public String getInfo() {
        return "Email: Hello World";
    }
}

// 方式1 分析
// 1 简单
// 2 但是如果这个时候接收的只是Email 有可能是微信 短信等等呢？
// 这样就会无限增加类 Person也要增加方法
// 3 解决思路：引入一个抽象接口，IReceiver 表示接收者
// Person 和 IReceiver 就是在一起有依赖
class PersonA {
    public void recieve(EmailA email) {
        System.out.println(email.getInfo());
    }
}
```

**进化后**

```java
public class DependenceInversionB {
    public static void main(String[] args) {
        PersonB p = new PersonB();
        p.receive(new EmailB()); // Email....
        p.receive(new Wechat()); // Wechat....
    }
}

interface IReceiver {
    public String getInfo();
}

// 这个方式是EmailB
class EmailB implements IReceiver {
    @Override
    public String getInfo() {
        return "Email....";
    }
}

// 这个方式如果是Wechat
class Wechat implements IReceiver {
    @Override
    public String getInfo() {
        return "Wechat....";
    }
}

class PersonB {
    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}
```

依赖关系传递的3种方式

- 接口传递
- 构造方法传递
- setter方式传递

```java
public class DependenceInversionC {
    public static void main(String[] args) {
        // // A 通过接口
        // OpenClose oc = new OpenClose();
        // oc.open(new Sony()); // Sony open

        // // B 通过构造方法
        // Sony s = new Sony();
        // OpenClose oc = new OpenClose(s);
        // oc.open(); // Sony open

        // C 通过setter方法传递
        OpenClose oc = new OpenClose();
        oc.setTV(new Sony());
        oc.open(); // Sony open


    }
}

// // A 通过接口
// interface SwitchOpenClose {
//     void open(TV tv);
// }

// interface TV {
//     void play();
// }

// // 实现接口
// class OpenClose implements SwitchOpenClose {
//     @Override
//     public void open(TV tv) {
//         tv.play();
//     }
// }

// SONY实现
class Sony implements TV {
    @Override
    public void play() {
        System.out.println("Sony open");
    }
}

// // B 通过构造方法
// interface SwitchOpenClose {
//     void open();
// }

// interface TV {
//     void play();
// }

// class OpenClose implements SwitchOpenClose {
//     public TV tv;
//     // 这里通过构造器，这里需要传入一个实现类
//     public OpenClose(TV tv) {
//         this.tv = tv;
//     }
//     @Override
//     public void open() {
//         this.tv.play();
//     }
// }

// C 通过setter方法传递
interface SwitchOpenClose {
    void open();
    // 抽象方法，这里必须要实现，就会通过set方法进行初始化了
    void setTV(TV tv);
}

interface TV {
    void play();
}

class OpenClose implements SwitchOpenClose {
    // 这里是私有的
    private TV tv;
    public void setTV(TV tv) {
        this.tv = tv;
    }
    @Override
    public void open() {
        this.tv.play();
    }
}
```

### 里氏替换原则（Liskov Substitution）

这个主要是对**继承**的一次大考验。主要就是说，如果子类继承了父类的方法

- 全部重写 → 那干嘛继承
- 重写太多 → 那样父类如果变化了 子类怎么办 重写的越多耦合性多大
- **结论就是尽量不要重写父类的方法** 如果要改 就需要组合，聚合这样

A类有1234方法 B类重写123方法 按照这个理论的话 那么建议A类和B类弄一个上层的更加BASE类。A和B之间的基础方法都给这个BASE类。

那样就可以解耦合。聚合，组合，依赖这样来解决问题。

**进化前**

```java
public class LiskovSubstitution {
    public static void main(String[] args) {

        LiskovA a = new LiskovA();
        System.out.println("11 - 3 = " + a.func1(11, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        LiskovB b = new LiskovB();
        // 这里的b是重写了func1 但是很容易就会忘记你已经重写了
        System.out.println("11 - 3 = " + b.func1(11, 3));
        System.out.println("1 - 8 = " + b.func1(1, 8));
        System.out.println("11 + 3 + 9 = " + b.func2(11, 3));

    }
}

/**
 * A类
 */
class LiskovA {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

/**
 * B类
 * 增加了一个新功能 在ab相加的基础上+9
 */
class LiskovB extends LiskovA {
    // 这里可以无意识重写了
    // 这时候就不满足里氏替换原则
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }
}
```

**进化后**

A和B 这里基本解耦 

```java
public class LiskovSubstitutionB {
    public static void main(String[] args) {
        LiskovAA a = new LiskovAA();
        System.out.println("11 - 3 = " + a.func1(11, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        System.out.println("-------------------");
        LiskovBB b = new LiskovBB();
        // 因为BB不在继承AA类 所以调用不会再认为func1是减法了
        // 调用的功能就会明确
        System.out.println("11 + 3 = " + b.func1(11, 3));
        System.out.println("1 + 8 = " + b.func1(1, 8));

        // 使用组合依然可以使用到AA的相关方法
        // 这里的func3 其实调用的是AA类的
        System.out.println("11 - 3" + b.func3(11, 3));

    }
}


class LiskovBase {
}

/**
 * AA类
 */
class LiskovAA extends LiskovBase {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

/**
 * BB类
 */
class LiskovBB extends LiskovBase {
    // 如果这个时候BB要使用AA的func1
    private LiskovAA a = new LiskovAA();

    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    public int func3(int a, int b) {
        return this.a.func1(a, b);
    }
}
```

