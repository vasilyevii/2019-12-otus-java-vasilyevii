package com.vasilyevii;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void runTest(String className) {

        List<Method> beforeMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();

        try {

            Class<?> testClass = Class.forName(className + "Test");

            Constructor<?> constructor = testClass.getConstructor();
            Object obj = constructor.newInstance();

            for (Method method : testClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Before.class)) {
                    beforeMethods.add(method);
                } else if (method.isAnnotationPresent(Test.class)) {
                    testMethods.add(method);
                } else if (method.isAnnotationPresent(After.class)) {
                    afterMethods.add(method);
                }
            }

            invokeMethod(beforeMethods, obj);
            invokeMethodWithStat(testMethods, obj);
            invokeMethod(afterMethods, obj);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void invokeMethod(List<Method> methods, Object obj) {

        for (Method method : methods) {
            try {
                method.invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void invokeMethodWithStat(List<Method> methods, Object obj) {

        int failedCount = 0;

        for (Method method : methods) {
            try {
                method.invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
                failedCount++;
            }
        }

        System.out.println("Total test: " + methods.size() + ", success: " + (methods.size() - failedCount) + ", fail: " + failedCount);

    }


}
