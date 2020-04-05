package com.vasilyevii.factory;

public class CashBucketVendorB implements CashBucket {

    private String vendor = "B";
    private double balance = 0;

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    @Override
    public String getVendor() {
        return vendor;
    }
}
