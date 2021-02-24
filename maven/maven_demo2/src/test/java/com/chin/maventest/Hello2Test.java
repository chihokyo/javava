package com.chin.maventest;

import org.junit.Test;

public class Hello2Test {

    @Test
    public void testHello2() {
        Hello2 hello2 = new Hello2();
        String result = hello2.sayHello2("MAVEN");
        System.out.println(result);    
    }
}
