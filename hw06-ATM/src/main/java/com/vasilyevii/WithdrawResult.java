package com.vasilyevii;

import java.util.Map;

public class WithdrawResult {

    public final boolean result;
    public final String message;
    public final Map<Denominations, Integer> withdrawBanknotes;

    public WithdrawResult(boolean result, String message, Map<Denominations, Integer> withdrawBanknotes) {
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
