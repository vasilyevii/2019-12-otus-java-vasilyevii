package com.vasilyevii;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // factory, bridge
        ArrayList<ATM> atms = new ArrayList<>();
        atms.add(ATMFactory.getATM("One", "A"));
        atms.add(ATMFactory.getATM("One", "B"));
        atms.add(ATMFactory.getATM("Two", "A"));
        atms.add(ATMFactory.getATM("Two", "B"));

        Department department = new Department(atms);

        // command
        department.getATMsBalance();

        // state
        department.getATMsState();
        atms.forEach(atm -> atm.changeState());
        department.getATMsState();

        // observer
        ATMEventProducer atmEventProducer = new ATMEventProducer();
        atms.forEach(atm -> atmEventProducer.addListener(atm));
        atmEventProducer.event("restart");

        department.getATMsState();

    }
}
