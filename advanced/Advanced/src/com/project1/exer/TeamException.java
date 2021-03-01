package com.project1.exer;

/**
 * 自定义异常
 */
public class TeamException extends Exception {

    private static final long serialVersionUID = 7722299564919711424L;

    public TeamException() {

    }

    public TeamException(String message) {
        super(message);
    }
}
