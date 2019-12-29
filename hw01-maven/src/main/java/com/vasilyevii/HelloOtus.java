package com.vasilyevii;

import com.google.common.base.Preconditions;

public class HelloOtus {

    public static void main(String[] args) {

        System.out.println("Guava using example:");

        Object obj = null;
        Preconditions.checkNotNull(obj, "Object is NULL", 404);

    }

}
