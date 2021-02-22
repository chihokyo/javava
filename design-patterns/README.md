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

