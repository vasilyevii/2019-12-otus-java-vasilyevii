package ru.otus;


public class Demo {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        FirstThread firstThread = new FirstThread(counter);
        SecondThread secondThread = new SecondThread(counter);

        firstThread.thread.start();
        secondThread.thread.start();

        Thread.sleep(3000);
    }
}

class FirstThread implements Runnable {

    Counter counter;
    Thread thread;

    public FirstThread(Counter counter) {
        this.counter = counter;
        this.thread = new Thread(this, "First thread");
        this.thread.setDaemon(true);
    }

    @Override
    public void run() {

        int i = 0;
        boolean ascend = true;

        while (true) {
            if (i == 1) {
                ascend = true;
            } else if (i == 10) {
                ascend = false;
            }
            if (ascend) {
                i++;
            } else {
                i--;
            }
            counter.firstThreadCount(i);
        }
    }
}

class SecondThread implements Runnable {

    Counter counter;
    Thread thread;

    public SecondThread(Counter counter) {
        this.counter = counter;
        this.thread = new Thread(this, "Second thread");
        this.thread.setDaemon(true);
    }

    @Override
    public void run() {

        int i = 0;
        boolean ascend = true;

        while (true) {
            if (i == 1) {
                ascend = true;
            } else if (i == 10) {
                ascend = false;
            }
            if (ascend) {
                i++;
            } else {
                i--;
            }
            counter.secondThreadCount(i);
        }
    }
}

class Counter {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    boolean switcher = false;

    synchronized void firstThreadCount(int n) {

        while (switcher) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(ANSI_RED + Thread.currentThread().getName() + ": " + n + ANSI_RESET);
        switcher = true;
        notify();
    }

    synchronized void secondThreadCount(int n) {

        while (!switcher) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(ANSI_BLUE + Thread.currentThread().getName() + ": " + n + ANSI_RESET);
        switcher = false;
        notify();
    }

}


