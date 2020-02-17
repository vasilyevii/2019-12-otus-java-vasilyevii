package com.vasilyevii;

public class TestLogging implements TestLoggingInterface {

    @Log
    @Override
    public void calculation(int param) {
        System.out.println("business logic here");
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        System.out.println("business logic here");
    }
}
