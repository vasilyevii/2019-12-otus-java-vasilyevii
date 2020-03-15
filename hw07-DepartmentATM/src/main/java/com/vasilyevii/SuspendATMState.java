package com.vasilyevii;

public class SuspendATMState implements ATMState {
    @Override
    public ATMState action() {
        System.out.println("Suspended...");
        return ATMStateProvider.getInitATMState();
    }

    @Override
    public String getStateName() {
        return "Suspended";
    }
}
