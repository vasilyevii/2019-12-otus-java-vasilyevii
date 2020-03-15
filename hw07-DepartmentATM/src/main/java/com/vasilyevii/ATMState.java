package com.vasilyevii;

public interface ATMState {
    public ATMState action();
    public String getStateName();
}
