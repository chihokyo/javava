import org.graalvm.compiler.lir.aarch64.AArch64ControlFlow.BitTestAndBranchOp;

/**
 * 强制类型转换： 自动类型提升运算的逆运算 
 * 1. 需要使用强转符() 
 * 2. 可能导致精度巡视
 */

public class TypeConversion2 {
    public static void main(String[] args) {
        // 精度损失案例1
        double d3 = 12.2;
        double d2 = 12.9;
        int i1 = (int) d3; // 大转小
        int i2 = (int) d2; // 大转小
        System.out.println(i1); // 12
        System.out.println(i2); // 12 不是四舍五入 切り捨て 截断 损失精度

        // 没有精度损失
        long l1 = 123l;
        short s1 = (short) l1;
        System.out.println(s1); // 123

        // 精度损失案例2
        int i3 = 128;
        byte b1 = (byte) i3;
        System.out.println(b1); // -128

        //*******************************
        // 1.编码情况
        // 为什么不加L不会报错，因为这个时候强制类型转换成了int，就不会报错
        long l = 144;
        System.out.print(l);
        long veryL = 144444444444L; // 太长的就必须加了，因为int受不住

        //2.同理的float
        //float f1 = 12.3;//  一定要加上F，

        //3.常量问题
        // 整型常量默认就是int型
        // 浮点型常量默认就是double型
        byte b = 2;
        // byte b1 = b + 1;
        int b2 = b +  1;
        System.out.println(b2);
        // 1其实在这里是一个整型常量，最后的结果也必须是一个int

        // float f1 = b + 12.34; //不行
        double d3 = b + 12.444;
    }

}
