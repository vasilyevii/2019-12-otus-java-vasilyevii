package com.vasilyevii;

import java.util.*;

class BagOfPrimitives {

    private int a;
    private String nullHere;
    private Object[] b;
    private List<Object> arrayList = new ArrayList<>() ;
    private TestClass testClass = new TestClass("some text");
    private Map<String, TestClass> map = new HashMap<>();
    private String badField = null;

    public BagOfPrimitives() {
        this.b = new Object[]{"one", "two", new String[]{"three - one", "three - two"}};
        this.arrayList.add("three");
        this.arrayList.add("four");
        this.arrayList.add(Arrays.asList("five - one", "five - two"));
        this.map.put("one-key", new TestClass("one-value"));
        this.map.put("two-key", new TestClass("two-value"));
    }

}
