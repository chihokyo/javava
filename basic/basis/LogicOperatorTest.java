package basis;

/**
 * 逻辑运算符练习相关
 */
public class LogicOperatorTest {
    public static void main(String[] args) {

        /******练习题1********/ 
        int x = 1, y = 1;
        //　① 先拿x和2比较，是false
        //　② x++　x成为2
        //　③ ++y y成为2 是true
        //　④ false & true 是false ，所以并不会把7赋值给x
        if (x++ == 2 & ++y == 2) {
            x = 7;
        }
        System.out.println("x=" + x + ",y=" + y);

        /******练习题2********/ 
        int x2 = 1, y2 = 1;
        //　① 先拿x和2比较，是false
        //　② x++　x成为2
        //　③ 因为是短路判断，后面的y并不会执行
        if (x2++ == 2 && ++y2 == 2) {
            x2 = 7;
        }
        System.out.println("x2=" + x2 + ",y2=" + y2);

        /******练习题3********/ 
        int x3 = 1, y3 = 1;
        //　① 先拿x和1比较，是true
        //　② x++　x成为2
        //　③ 非短路操作，继续 ++y3自加是2 判断为false
        //　④ false | true true ，赋值x3为7
        if (x3++ == 1 | ++y3 == 1) {
            x3 = 7;
        }
        System.out.println("x3=" + x3 + ",y3=" + y3);

        /******练习题4********/ 
        int x4 = 1, y4 = 1;
        //　① 先拿x和1比较，是true
        //　② x++　x成为2
        //　③ 短路操作，后面代码无视，y并不会增加
        if (x4++ == 1 || ++y4 == 1) {
            x4 = 7;
        }
        System.out.println("x4=" + x4 + ",y4=" + y4);
    }
    
}

class LogicOperatorTest2 {
    public static void main(String[] args) {
        boolean x = true;
        boolean y = false;
        short z = 42;
        // 先比较然后+，z：43
        // true && true 所以z：44
        if((z++ == 42) && (y=true)) z++;
        // false 先+在比较 z:45
        // false || true z：46
        if((x == false) || (++z == 45)) z++;
        System.out.println("z=" + z); // 46
    }
}