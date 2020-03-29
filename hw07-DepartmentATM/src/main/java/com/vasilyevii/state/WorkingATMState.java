package com.vasilyevii.state;

public class WorkingATMState implements ATMState {
    @Override
    public ATMState action() {
        System.out.println("Working...");
        return ATMStateProvider.getSuspendATMState();
    }

    @Override
    public String getStateName() {
        return "Working";
    }
}
