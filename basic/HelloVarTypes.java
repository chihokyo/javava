/**
 * 数据类型
 * 1） 按照数据类型分类
 * 基本数据类型【primitive type】
 *      数值型
 *          整数（byte，short，int，log）→ 通常使用 int
 *          浮点（float，double）
 *      字符型（char）
 *      布尔型（boolena）
 * 
 * 引用数据类型【reference type】
 *      类（class）→ 字符串在这里
 *      接口（interface）
 *      数组（[]）
 */
// package basic;

public class HelloVarTypes {
    public static void main(String[] args) {
        /* 整型

        ① 数字整型都有范围 通常用int就足够了
        ② long结尾必须是 lorL
        */
        String bytebyte = "1字节=8bit，大概是2的8次方，-128-172之间";
        String shortshort = "2字节=16bit，大概是2的16次方，-65536-65536之间";
        String intint = "4字节=32bit，大概是2的32次方，";
        String longlong = "8字节=64bit，大概是2的64次方，必须以l或者L结尾";
        System.out.println(bytebyte);
        System.out.println(shortshort);
        System.out.println(intint);
        System.out.println(longlong);
 
        /*  浮点型

        ① float （4字节） 必须以 f or F 结尾
        ② double（8字节）
        ③ float 表示范围 大于 long
        ④ 通常定义浮点型用 double
        */
        float f1 = 12.3F;
        double d1 = 1.234;
        System.out.println(f1);
        System.out.println(d1);

        /*  字符型

        ① 单引号 ‘’
        ② 只能1个字符
        ③ 声明1个字符，转译字符，直接使用 Unicode表示字符型常量
        */

        // char c1 = "A" // 双引号 out
        // char c2 = 'AB' // 1个字符 out
        char c3 = 'C';
        char c4 = '\n';
        char c5 = '\u0043';
        System.out.println("c3: " + c3);
        System.out.println("c4: " + c4);
        System.out.println("c5: " + c5);
        System.out.println("换行符：" + c4);

        /*  布尔型 Boolean
        
        ① true or false 俩
        ② 判断条件，循环常用
        */

        boolean isAnimal = true;
        System.out.println(isAnimal);
        boolean isPerson = false;
        if (isPerson) {
            System.out.println("I am Human");
        } else {
            System.out.println("WangWang!");
        }

    }
}