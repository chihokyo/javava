package com.chin.maventest;

public class Hello2 {
    
    public String sayHello2(String name) {
        
        Hello hello = new Hello();
        String str = hello.sayHello(name) + "I am " + this.getMyName();
        return str;
    }

    public String getMyName() {
        return "Vscode";
    }
}
