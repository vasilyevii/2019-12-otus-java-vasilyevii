package com.vasilyevii;

abstract public class ATM implements Listener {

    private final CashBucket cashBucket;
    private ATMState state;

    public ATM(CashBucket cashBucket, ATMState initState) {
        this.cashBucket = cashBucket;
        this.state = initState;
    }

    public String getBalance() {
        return this + " - Balance: " + cashBucket.getBalance();
    }

    public void deposit(double amount) {
        cashBucket.deposit(amount);
    }

    public void withdraw(double amount) {
        cashBucket.withdraw(amount);
    }

    public CashBucket getCashBucket() {
        return cashBucket;
    }

    public String getCashBucketVendor() {

        if (cashBucket != null) {
            return cashBucket.getVendor();
        } else {
            return "---";
        }
    }

    public void changeState() {
        this.setState(state.action());
    }

    public void setState(ATMState state) {
        this.state = state;
    }

    public String getStateName() {
        return this + " - State: " + this.state.getStateName();
    }

    public void eventHandler(String event) {
        if (event.equals("restart")) {
            this.setState(ATMStateProvider.getInitATMState());
        }
    }
}
