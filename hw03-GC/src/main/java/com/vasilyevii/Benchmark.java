package com.vasilyevii;


import java.util.ArrayList;
import java.util.List;


class Benchmark implements BenchmarkMBean {
    private final int loopCounter;
    private volatile int size = 0;
    private int idx;

    public Benchmark( int loopCounter ) {
        this.loopCounter = loopCounter;
    }

    public int getIdx() {
        return this.idx;
    }

    void run() throws InterruptedException {

        List<Object[]> cacheArrays = new ArrayList<>();

        for (idx = 0; idx < loopCounter; idx++) {
            int local = size;
            Object[] array = new Object[local];
            for (int i = 0; i < local; i++ ) {
                array[i] = new String(new char[0]);
            }
            Thread.sleep( 10 ); //Label_1

            if (idx % 20 == 0) {
                cacheArrays.add(array);
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize( int size ) {
        System.out.println( "new size:" + size );
        this.size = size;
    }

}
