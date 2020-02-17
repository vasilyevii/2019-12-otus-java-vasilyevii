package com.vasilyevii;

public class AutoLogDemo {

    public static void main(String[] args) {

        TestLoggingInterface testLogging = IoC.createMyClass();
        testLogging.calculation(6);
        testLogging.calculation(1, 2);

    }
}
