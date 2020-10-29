package com.errorexception.exer;
/*
 * 编写应用程序EcmDef.java，接收命令行的两个参数，要求不能输入负数，计算两数相除。
	对数据类型不一致(NumberFormatException)、缺少命令行参数(ArrayIndexOutOfBoundsException、
  	除0(ArithmeticException)及输入负数(EcDef 自定义的异常)进行异常处理。
提示： 
	(1)在主类(EcmDef)中定义异常方法(ecm)完成两数相除功能。
	(2)在main()方法中使用异常处理语句进行异常处理。
	(3)在程序中，自定义对应输入负数的异常类(EcDef)。
	(4)运行时接受参数 java EcmDef 20 10   //args[0]=“20” args[1]=“10”
	(5)Interger类的static方法parseInt(String s)将s转换成对应的int值。
        如：int a=Interger.parseInt(“314”);	//a=314;

*/
public class EcDefTest {
    public static void main(String[] args) {
        try {
            int i = Integer.parseInt(args[0]);
            int j = Integer.parseInt(args[1]);
            int result = ecm(i, j);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException　数据类型不一样");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException 缺少命令行参数");
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException 除0");
        } catch (EcDef e) {
            System.out.println("EcDef" + e.getMessage());
        }
    }

    // 必须要 throws EcDef 交给上面进行 catch
    // 因为这不是运行时异常，所以编译阶段就抛到了上面
    public static int ecm(int i, int j) throws EcDef {
        if (i < 0 || j < 0) {
            // 手动抛出异常对象
            throw new EcDef("分子or分母成为负数了");
        }
        return i / j;
    }
}

// 自定义异常类
class EcDef extends Exception {

    static final long serialVersionUID = -7034897198845766939L;

    public EcDef() {
    }

    public EcDef(String msg) {
        super(msg);
    }
}
