package com.errorexception.exer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/*
 * 异常处理的方式二：throws + 异常类型
 * 
 * 1. "throws + 异常类型"写在方法的声明处。指明此方法执行时，可能会抛出的异常类型。
 *     一旦当方法体执行时，出现异常，仍会在异常代码处生成一个异常类的对象，此对象满足throws后异常
 *     类型时，就会被抛出。异常代码后续的代码，就不再执行！
 *     
 * 2. 体会：try-catch-finally:真正的将异常给处理掉了。
 *        throws的方式只是将异常抛给了方法的调用者。  并没有真正将异常处理掉。  
 * 
 * 3. 开发中如何选择使用try-catch-finally 还是使用throws？
 *   3.1 如果父类中被重写的方法没有throws方式处理异常，则子类重写的方法也不能使用throws，意味着如果
 *       子类重写的方法中有异常，必须使用try-catch-finally方式处理。【父类范围大，子类范围要小于父类】
 *   3.2 执行的方法a中，先后又调用了另外的几个方法，这几个方法是递进关系执行的。我们建议这几个方法使用throws
 *       的方式进行处理。而执行的方法a可以考虑使用try-catch-finally方式进行处理。
 *      【因为异常如果提前在最小的子类进行处理掉，那么就有可能找不到上面的异常了】
 * 
 * 异常并非就解决了code问题，只是为了友好的展现给用户。
 * 
 */
public class ThrowsExceptionTest {
    public static void main(String[] args) {

        ThrowsExceptionTestClass t = new ThrowsExceptionTestClass();
        // 这样继续抛下去也不合适了
        // 还是要处理的
        try {
            t.methodB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThrowsExceptionTestClass {

    public void methodC() {

        // 因为这里已经给处理了，所以不会向上抛了
       try {
        methodB();
       } catch (Exception e) {
            e.printStackTrace();
       }
    }

    public void methodB() throws IOException {
        methodA();
    }
    // 自己不处理，抛给调用的上一级进行处理
    public void methodA() throws FileNotFoundException, IOException {

        File file = new File("hello.txt");
        FileInputStream fis = new FileInputStream(file);

        int data = fis.read();
        while(data != -1){
            System.out.println((char)data);
            data = fis.read();
        }  

        fis.close();

        System.out.println("不同于 finally 这里是不会出现的在执行的");

    }
}
