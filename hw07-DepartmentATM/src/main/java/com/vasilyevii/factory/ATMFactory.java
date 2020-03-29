package com.vasilyevii.factory;

public class ATMFactory {

    public static ATM getATM(String ATMType, String cashBucketVendor)  {

        CashBucket cashBucket = null;
        if (cashBucketVendor.equals("A")) {
            cashBucket = new CashBucketVendorA();
        } else if (cashBucketVendor.equals("B")) {
            cashBucket = new CashBucketVendorB();
        } else {
            throw new IllegalArgumentException("unknown param: " + cashBucketVendor);
        }

        if (ATMType.equals("One")) {
            return new ATMTypeOne(cashBucket);
        } else if (ATMType.equals("Two")) {
            return new ATMTypeTwo(cashBucket);
        }

        throw new IllegalArgumentException("unknown param: " + ATMType);

    }
}
