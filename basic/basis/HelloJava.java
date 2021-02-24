package basis;

/*
1. 程序的入口是main()函数，格式固定。除了args可以换成任意参数。和string后面的数组可以添加在[]之外
2. 输出语句有2个，一个换行，一个不换行
3. 在一个 java 源文件当中可以声明多个 class 但是 public 有且只有一个和原文件名重名 其他不能写 public
4. 执行语句要以；结束 严格区分大小姐
5. 编译过程 字节码文件个数 和 类名个数名字 相同 
*/


/**
 * @author chin
 * @version 1.1.1
 * 这里是文档注释，可以直接生成index目录
 */
public class HelloJava {
    public static void main(String[] args) {
        System.out.print("hello java");
        System.out.print("hello java");
        System.out.print("hello java");
        System.out.println("hello java with new line");
        System.out.println("hello java with new line");
        System.out.println("hello java with new line");
    }    
}

class Animal {
    public static void main(String[] args) {
        System.out.println("Animal Class");
    }
}

class Person {
    public static void main(String[] args) {
        System.out.println("Person Class");
    }
}