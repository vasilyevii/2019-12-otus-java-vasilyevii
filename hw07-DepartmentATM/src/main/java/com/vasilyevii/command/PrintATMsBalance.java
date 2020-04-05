package com.vasilyevii.command;

import com.vasilyevii.factory.ATM;
import java.util.ArrayList;

public class PrintATMsBalance implements Command {

    @Override
    public String execute(Object data) {
        double balance = 0;
        ArrayList<ATM> atms = (ArrayList<ATM>) data;
        for (ATM atm : atms) {
            balance += atm.getBalance();
        }
        return String.valueOf(balance);
    }
}