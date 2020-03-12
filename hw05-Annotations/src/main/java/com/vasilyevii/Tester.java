package com.vasilyevii;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void runTest(String className) {

        Class<?> testClass;

        try {
            testClass = Class.forName(className + "Test");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        List<Method> beforeMethods = getClassMethodsByAnnotation(testClass, Before.class);
        List<Method> afterMethods = getClassMethodsByAnnotation(testClass, After.class);
        List<Method> testMethods = getClassMethodsByAnnotation(testClass, Test.class);

        int failedCount = 0;

        for (Method testMethod : testMethods) {

            Object obj;

            // getting a new object for each test method
            try {
                obj = testClass.getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // if before method throws an exception then don't run the test and after method
            try {
                invokeMethods(beforeMethods, obj);
            } catch (Exception e) {
                e.printStackTrace();
                failedCount++;
                continue;
            }

            // invoke test method
            try {
                testMethod.invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
                failedCount++;
            }

            // invoke after-methods even if the test was failed
            try {
                invokeMethods(afterMethods, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println("Total test: " + testMethods.size() + ", success: " + (testMethods.size() - failedCount) + ", fail: " + failedCount);

    }

    private static void invokeMethods(List<Method> methods, Object obj) throws Exception {
        for (Method method : methods) {
            method.invoke(obj);
        }
    }

    private static List<Method> getClassMethodsByAnnotation(Class testClass, Class annotationClass) {

        var methods = new ArrayList<Method>();

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                methods.add(method);
            }
        }
        return methods;
    }
}
