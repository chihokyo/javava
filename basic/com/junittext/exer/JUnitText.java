package com.junittext.exer;

/**
 * Java的Unit单元测试
 * 1 步骤 vscode 加载插件即可
 * 2 创建java类进行单元测试 
 *      2-1 public class 
 *      2-2提供公共无参构造器
 *      2-3 书写测试方法 方法权限public 没有返回值，没有形参，
 *      2-4 单元测试方法上需要增加注解@Test 导入import
 */

public class JUnitText {

    // 方法权限public 没有返回值，没有形参
    @Test
    public void testEquals() {
        String s1 = "MM";
        String s2 = "MM";
        System.out.println(s1.equals(s2));
    }
}
