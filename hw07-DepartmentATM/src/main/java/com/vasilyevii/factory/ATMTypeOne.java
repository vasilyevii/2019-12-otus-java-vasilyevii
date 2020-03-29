package com.vasilyevii.factory;

import com.vasilyevii.state.ATMStateProvider;

public class ATMTypeOne extends ATM {

    public ATMTypeOne(CashBucket cashBucket) {
        super(cashBucket, ATMStateProvider.getInitATMState());
    }

    @Override
    public String toString() {
        return "ATM type: One, cash bucket vendor: " + getCashBucketVendor();
    }

}
