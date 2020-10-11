/**
 * 进制系统 
 * 
 * 二进制 binary 以0b/0B开头 
 * 八进制 decimal 
 * 十进制 octal 以数字0开头 
 * 十六进制 hex 以0x/0X开头
 */

public class BinarySystem {
    public static void main(String[] args) {
        int num1 = 0b110;
        int num2 = 110;
        int num3 = 012;
        int num4 = 0xA;

        System.out.println("num1: " + num1); // 6
        System.out.println("num2: " + num2); // 110
        System.out.println("num3: " + num3); // 10
        System.out.println("num4: " + num4); // 10

    }
}
