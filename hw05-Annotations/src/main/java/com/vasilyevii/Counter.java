package com.vasilyevii;

public class Counter {

    int initialValue = 10;

    int inc() {
        return initialValue++;
    }

    int dec() {
        return initialValue--;
    }

}
