package com.vasilyevii;

import java.util.HashMap;

public class WithdrawResult {

    public final boolean result;
    public final String message;
    public final HashMap<Denominations, Integer> withdrawBanknotes;

    public WithdrawResult(boolean result, String message, HashMap<Denominations, Integer> withdrawBanknotes) {
        this.result = result;
        this.message = message;
        this.withdrawBanknotes = withdrawBanknotes;
    }

    @Override
    public String toString() {
        return "WithdrawResult{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", withdrawBanknotes=" + withdrawBanknotes +
                '}';
    }
}
