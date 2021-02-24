package com.reflect.exer;

import java.lang.reflect.*;

/**
 * 获取运行时类的方法
 */
public class ReflectionTest6 {
    public static void main(String[] args) {

        Class<Animal> clazz = Animal.class;
        // 获取方法
        // 1 getMethods() 获取当前运行类以及父类声明为public权限的方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("*******华丽的分割线*******");

        // getDeclaredMethods(); 获取当前运行时类的所有声明方法（不包含父类）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }

        System.out.println("*******华丽的分割线*******");

        // 权限修饰符，返回值类型 方法名等等

        Method[] dMethods = clazz.getDeclaredMethods();

        for (Method m : dMethods) {

            // 1 权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            // 2 返回值类型
            System.out.print(m.getReturnType().getName() + "\t");

            // 3 方法名
            System.out.print(m.getName() + "\t");

            // 4 形参列表
            System.out.print("(");
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    // 这里是判断是否为最后一个参数
                    if (i == parameterTypes.length - 1) {
                        System.out.print(parameterTypes[i].getName() + "args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + "args_" + i + ",");
                }
            }

            System.out.print(")");
            System.out.println();

            // 5 抛出的异常
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1) {
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ", ");
                }
            }

            System.out.println();

        }

    }
}
