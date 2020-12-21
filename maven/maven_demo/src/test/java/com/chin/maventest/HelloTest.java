package com.chin.maventest;

import org.junit.Test;

public class HelloTest {
    @Test
    public void testHello() {
        Hello hello = new Hello();
        String maven = hello.sayHello("CHIN");
        System.out.println(maven);
    }
}
