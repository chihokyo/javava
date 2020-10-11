/**
 * 变量
 * 1. 声明
 * 2. 类型
 * 3. 赋值
 * 
 * 变量使用
 * 1 必须先声明，后使用
 * 2 变量必须定义在作用域内，作用域外失效（就是定义的{}）
 * 3 同一个作用域内，不能用2个重名的变量
 */

public class HelloVarible {
    public static void main(String[] args) {
        // 声明 + 赋值
        int myNumber = 12;
        // 先声明 → 后赋值
        String name;
        name = "Varible";
        // 使用变量
        System.out.println(myNumber);
        System.out.println(name);
    }
}

class VaribleErrorTest1 {
    public static void main(String[] args) {
        int myNumber = 12;
        // 这里不能先进行使用
        // 编译错误：并没有定义，内存中无法知道
        // System.out.println(myAge);
        System.out.println(myNumber);
        // int myAge = 20;
    }
}

// class VaribleErrorTest2 {
//     public static void main(String[] args) {
//         int myNumber = 12;
//         // 这里不能先进行使用
//         int myAge;
//         // 编译错误：虽然已定义，但未赋值，也不行
//         System.out.println(myAge);
//         myAge = 20;
//     }
// }