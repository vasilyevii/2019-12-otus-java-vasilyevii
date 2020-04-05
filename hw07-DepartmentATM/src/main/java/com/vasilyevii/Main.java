package com.vasilyevii;

import com.vasilyevii.factory.ATM;
import com.vasilyevii.factory.ATMFactory;

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
        department.printATMsBalance();

        // state
        department.printATMsState();
        atms.forEach(atm -> atm.changeState());
        department.printATMsState();

        // observer
        department.restartATMs();

        department.printATMsState();

    }
}
