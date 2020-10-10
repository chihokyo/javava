/**
 * 关于标识符的注意点
 * 1. 26个字母/0-9数组/下划线/$
 * 2. 数字不能开头
 * 3. 不可以使用关键字（keyword）和保留字（reserved word），但可以包含
 * 4. 严格区分大小写，无长度限制
 * 5. 不能有空格
 * 
 * 关于命名规范
 * 【包名】多个单词全部小写 isupdate
 * 【类名接口名】大驼峰 → 首字母大写 IsUpdate
 * 【变量名，方法名】大驼峰 → 第二个单词开始首字母大写 isUpdate
 * 【常量名】全部大写 ISUPDATE
 */

public class HelloIdentifier {
    public static void main(String[] args) {
        System.out.println("HelloIdentifier");
    }
}

// 可以包含下划线和$符号
class Hello_$ {
    public static void main(String[] args) {
        int myNumber = 15;
        System.out.println(myNumber);
    }
}