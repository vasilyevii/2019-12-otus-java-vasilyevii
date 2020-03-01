package com.vasilyevii;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        ATM atm = new ATM();

        // deposit
        HashMap<Denominations, Integer> banknotes = new HashMap<>();
        banknotes.put(Denominations.ONE_HUNDRED, 2);
        banknotes.put(Denominations.ONE_THOUSAND, 1);

        var rejected = atm.deposit(banknotes);
        System.out.println("atm: " + atm);
        System.out.println("rejected: " + rejected);

        // withdraw
        var result = atm.withdraw(1300);
        System.out.println("result: " + result);
        System.out.println("atm: " + atm);

    }

}
