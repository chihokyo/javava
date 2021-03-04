package com.design.pattern.factory.factorymethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PizzaStore {
    public static void main(String[] args) {
        String local = PizzaStore.getLocal();
        if (local.equals("beijing")) {
            new OrderBJPizza();
        } else if (local.equals("london")) {
            new OrderLDPizza();
        } else {
            System.out.println("local error");
        }
    }

    private static String getLocal() {

        String str = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input local: ");
            str = br.readLine();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;

    }
}
