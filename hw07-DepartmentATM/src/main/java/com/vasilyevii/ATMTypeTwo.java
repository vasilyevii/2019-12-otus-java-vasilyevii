package com.vasilyevii;

public class ATMTypeTwo extends ATM {

    public ATMTypeTwo(CashBucket cashBucket) {
        super(cashBucket, ATMStateProvider.getSuspendATMState());
    }

    @Override
    public String toString() {
        return "ATM type: Two, cash bucket vendor: " + getCashBucketVendor();
    }

    @Override
    public void eventHandler(String event) {
        if (event.equals("restart")) {
            super.setState(ATMStateProvider.getSuspendATMState());
        }
    }
}
