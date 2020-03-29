package com.vasilyevii;

import java.util.*;

public class BagOfPrimitives {

    private int a;
    Object[] b;
    List<Object> arrayList = new ArrayList<>() ;
    TestClass testClass = new TestClass("some text");
    Map<String, TestClass> map = new HashMap<>();

    public BagOfPrimitives() {
        this.b = new Object[]{"one", "two", new String[]{"three - one", "three - two"}};
        this.arrayList.add("three");
        this.arrayList.add("four");
        this.arrayList.add(Arrays.asList("five - one", "five - two"));
        this.map.put("one-key", new TestClass("one-value"));
        this.map.put("two-key", new TestClass("two-value"));
    }

}
