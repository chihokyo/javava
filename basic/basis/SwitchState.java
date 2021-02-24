package basis;

import java.util.Scanner;

/**
 * switch
 * 默认符合条件是继续向下执行的
 * 除非写上break
 */
public class SwitchState {
    public static void main(String[] args) {
        int number = 2;
        // 依次匹配 匹配就是 == 关系
        // 区别于ifelse 非boolean也可以
        // 可以匹配到后面这么多类型 byte short char int 枚举类型 string
        // 浮点型，布尔型不可以用！
        // 只能声明常量，不可以使用范围！
        // default可选的
        // default如果放在最上面，当所有的执行完毕就执行default
        // 不适用于多种情况 比如写个100个case
        switch (number) {
            case 0:
                System.out.println("0");          
                break;
            case 1:
                System.out.println("1");          
                break;
            case 2:            
                System.out.println("2");          
                break;
            case 3: 
                System.out.println("3");          
                break;
            default:
                System.out.println("null");
                // 最后1个可不加，因为执行完自动结束     
                break;
        }
    

        // ********合并写法*******
        // 没写break其实就是一直向下走，并不是新知识
        switch (number) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("不及格");
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                System.out.println("及格");
                break;
        }
        
    }
}
