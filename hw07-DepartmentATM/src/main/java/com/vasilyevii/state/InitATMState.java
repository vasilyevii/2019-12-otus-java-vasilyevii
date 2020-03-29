package com.vasilyevii.state;

public class InitATMState implements ATMState {
    @Override
    public ATMState action() {
        System.out.println("Initialization...");
        return ATMStateProvider.getWorkingATMState();
    }

    @Override
    public String getStateName() {
        return "Initialization";
    }
}
