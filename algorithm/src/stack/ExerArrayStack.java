package stack;

import java.util.Stack;

/**
 * 一个联系 leetcode上面的 
 * 20. 有效的括号 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足： 左括号必须用相同类型的右括号闭合。 
 * 左括号必须以正确的顺序闭合。 
 * 注意空字符串可被认为是有效字符串。
 */
public class ExerArrayStack {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 判断是否为左括号 是的话就押入
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                // 不是左括号的话判断是否为空
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                // 不为空的话，那么就弹出来
                // 这里不管是否匹配都已经弹出来了
                char topChar = stack.pop();
                // 弹出来和c作比较是否有匹配的
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }

        }
        // 最后看一下栈是否为空
        return stack.isEmpty();
    }

    /**
     * 测试类
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println((new ExerArrayStack()).isValid("(){}"));
        System.out.println((new ExerArrayStack()).isValid("()[]{}"));
        System.out.println((new ExerArrayStack()).isValid("({[}})"));
    }
}
