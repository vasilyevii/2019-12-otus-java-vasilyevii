package com.vasilyevii.state;

public interface ATMState {
    public ATMState action();
    public String getStateName();
}
