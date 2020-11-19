package com.reflect.exer;
/**
 * 关于Class类的理解
 * 
 * 1 javac exe命令以后都生成原始的1个或者多个字节码文件
 * 2 java exe 命令使原始的字节码文件解释运行，就是把字节码加载到内存中
 * 3 上面的加载过程，就是类的加载。
 * 4 加载到内存的类，就是运行时候的类，那就是属于 Class 类
 * 5 运行时候的类，那就是 Class 的一个实例对象
 * 6 在没有加载到内存之前，根本就是没有 Class的。正好也解释了，万物皆对象。
 * 
 * Class calzz = Person.class 其实就是这个Person类本身就是Class的一个实例
 * Class的实例就是一个运行类
 * 其实 类本身也是对象
 */
public class ReflectionTest2 {
    public static void main(String[] args) {
        /**
         * 这里其实第三种运用的最多
         * 因为第三种可以实现运行时进行加载
         */

        try {
            // *******获取 Class 实例的4种方式*******

            // 方式一 调用运行时类的属性 .class
            // 其实Class也是一个泛型
            Class<Person> clazz1 = Person.class;
            // 输出的就是类本身 泛型可写可不写
            System.out.println(clazz1); // class com.reflect.exer.Person

            // 方式二 通过运行时的对象 getClass();
            Person p1 = new Person();
            Class<?> clazz2 = p1.getClass();
            System.out.println(clazz2); // class com.reflect.exer.Person

            // 方式三 调用Class的静态方法:forName(String classPath)
            Class<?> clazz3 = Class.forName("com.reflect.exer.Person");
            // Class clazz3 = Class.forName("java.lang.String ");
            System.out.println(clazz3);

            // 方式四 使用类的加载器 ClassLoader
            ClassLoader cl = ReflectionTest2.class.getClassLoader();
            Class<?> clazz4 = cl.loadClass("com.reflect.exer.Person");
            System.out.println(clazz4);

            // 为什么都是 true 因为这些类的实例并没有new 一个
            // 其实都是指向了同一个【运行时类】
            // 一旦加载到内存里，就会缓存一段时间
            // 以上只是通过不同的方式获取运行时类
            // 地址指向的都是同一个
            // 【重点是获取】
            
            System.out.println(clazz1 == clazz2); // true
            System.out.println(clazz1 == clazz3); // true
            System.out.println(clazz1 == clazz4); // true

        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }
}
