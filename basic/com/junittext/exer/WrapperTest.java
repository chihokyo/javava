package com.junittext.exer;
/**
 * 包装类的使用
 * 1 java提供了8种数据类型的包装类，使得基本数据类型都有类的特征
 * 2 基本数据类型 包装类 string 相互转换
 */
public class WrapperTest {
    // 基本数据类型 → 包装类
    public static void main(String[] args) {
        System.out.println("************基本数据类型 int*****************");
        int num1 = 10;
        // System.out.println(num1.toString());
        // Integer int1 = new Integer(num1); // JDK9之后并不推荐使用这种方式进行转换，静态方法更快更好
        // System.out.println(int1.toString()); // 10
        // Integer int2 = new Integer("124");
        // System.out.println(int2.toString()); // 124

        Integer int3 = Integer.valueOf(num1);
        System.out.println(int3.toString()); // 10

        Integer int4 = Integer.valueOf("888");
        System.out.println(int4.toString()); // 888

        // Integer int5 = Integer.valueOf("888dd");
        // System.out.println(int5.toString()); // NG 报异常
        // java.lang.NumberFormatException: 只能使用纯数字

        System.out.println("************基本数据类型 float*****************");

        Float f1 = new Float(12.3F); // JDK9之后不推荐
        Float f2 = new Float("12.3");
        Float f3 = Float.valueOf(12.1F);
        Float f4 = Float.valueOf("12.1");

        System.out.println(f1); // 12.3
        System.out.println(f2); // 12.3
        System.out.println(f3); // 12.1
        System.out.println(f4); // 12.1

        System.out.println("************基本数据类型 Boolean*****************");

        Boolean b1 = new Boolean(true); // JDK9之后不推荐
        Boolean b2 = new Boolean("true");
        Boolean b3 = Boolean.valueOf(true);
        Boolean b4 = Boolean.valueOf("true");
        Boolean b5 = Boolean.valueOf("TrUE"); // 不区分大小写，都是true
        Boolean b6 = Boolean.valueOf("true1223"); // 只要不是true 就全部是false

        System.out.println(b1); // true
        System.out.println(b2); // true
        System.out.println(b3); // true
        System.out.println(b4); // true
        System.out.println(b5); // true
        System.out.println(b6); // false

        // PS：如果使用的是对象进行调用，那么默认值原来是false，现在就是null
        Sample s = new Sample();
        // boolean isPrimitive;
        // Boolean isObject;
        System.out.println(s.isPrimitive); // false
        System.out.println(s.isObject); // null

        System.out.println("************包装类 Integer*****************");

        Integer intObj = Integer.valueOf(12);
        int intobjN = intObj.intValue();
        System.out.println(intobjN); // 12 这样就可以做加减乘除运算了

        System.out.println("************包装类 Float*****************");

        Float fObj = Float.valueOf(12.8F);
        float fObjN = fObj.floatValue();
        System.out.println(fObjN + 1); // 13.8

        System.out.println("************自动装箱 自动拆箱*****************");

        // 5.0新特性 自动装箱 自动拆箱
        // 不需要主动转换，自动转换类型

        /********** 自动装箱 **********/
        int intP1 = 100;
        Integer intP2 = intP1;

        boolean bool1 = true;
        Boolean boolObj1 = bool1;

        System.out.println(intP2); // 100
        System.out.println(boolObj1); // true

        /********** 自动拆箱 **********/

        Integer intObj2 = Integer.valueOf(200);
        int intP3 = intObj2;
        System.out.println(intP3 + 1); // 201

        Boolean boolObj2 = Boolean.valueOf(false);
        boolean bool2 = boolObj2;
        System.out.println(bool2); // false

        System.out.println("************转换到String类*****************");
        // 因为自动装箱pr拆箱的问题，所以现在可以上面2者转换到string类并没有多少区别
        // 现在只是写string转换就可以了

        // 方法1
        Integer inteObj = Integer.valueOf(678);
        String inteStr = inteObj + "";
        System.out.println(inteStr); // 678
        // 方法2
        Integer inteObj2 = Integer.valueOf(567);
        String inteStr2 = String.valueOf(inteObj2);
        System.out.println(inteStr2); // 567

        Boolean bb1 = Boolean.valueOf(true);
        String bbStr1 = String.valueOf(bb1);
        System.out.println(bbStr1); // true

        System.out.println("************String类转换到其他类型*****************");
        String str1 = "124";
        // String str1 = "124aa"; NG
        // 错误写法
        // int num1 = (int)str1;
        // Integer num1 = (Integer)str1;

        int strToint = Integer.parseInt(str1);
        System.out.println(strToint); // 124

        String str2 = "true";
        String str3 = "true124";
        boolean boolToStr = Boolean.parseBoolean(str2);
        boolean boolToStr2 = Boolean.parseBoolean(str3);
        System.out.println(boolToStr); // true
        System.out.println(boolToStr2); // false

    }

}

class Sample {
    boolean isPrimitive;
    Boolean isObject;
}
