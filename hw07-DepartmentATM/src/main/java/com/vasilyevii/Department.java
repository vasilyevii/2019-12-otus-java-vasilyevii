package com.vasilyevii;

import java.util.ArrayList;

public class Department {

    private ArrayList<ATM> atms;

    public Department(ArrayList<ATM> atms) {
        this.atms = atms;
    }

    public void getATMsBalance() {
        atms.stream().map(atm -> atm.getBalance()).forEach(System.out::println);
    }

    public void getATMsState() {
        atms.stream().map(atm -> atm.getStateName()).forEach(System.out::println);
    }
}
