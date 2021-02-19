# 设计模式(Desing Patterns)

## 写在前面的

设计模式感觉蛮有用的，具体代码写逻辑的时候感觉算法有用。在实际上写一个项目的时候感觉设计模式有用。

## 七大原则

#### 单一职责原则(Single Responsibility Principle-SRP)

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

