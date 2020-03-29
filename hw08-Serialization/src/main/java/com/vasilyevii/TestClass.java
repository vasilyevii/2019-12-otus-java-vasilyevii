package com.vasilyevii;

public class TestClass {
    private String a;

    public TestClass(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "a='" + a + '\'' +
                '}';
    }
}
