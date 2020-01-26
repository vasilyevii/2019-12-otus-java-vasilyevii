package com.vasilyevii;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Test DITArrayList:");

        List<Integer> myArrayList = new DIYArrayList<>();

        // Collections.addAll(Collection<? super T> c, T... elements)
        Collections.addAll(myArrayList, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);
        System.out.println(myArrayList);

        // Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
        List<Integer> newArrayList = new DIYArrayList<>(myArrayList.size());
        Collections.copy(newArrayList, myArrayList);
        System.out.println("Copied list: " + newArrayList);

        // Collections.static <T> void sort(List<T> list, Comparator<? super T> c)
        Collections.sort(newArrayList, (Integer o1, Integer o2) -> o2.compareTo(o1));
        System.out.println("Sorted list: " + newArrayList);

    }

}
