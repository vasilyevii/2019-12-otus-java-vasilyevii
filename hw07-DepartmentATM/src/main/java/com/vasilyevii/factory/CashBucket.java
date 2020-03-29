package com.vasilyevii.factory;

public interface CashBucket {

    public double getBalance();
    public void deposit(double amount);
    public void withdraw(double amount);
    public String getVendor();

}
