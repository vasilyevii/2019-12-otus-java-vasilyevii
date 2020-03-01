package com.vasilyevii;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class ATM {

    private final Set<Cell> cells = new TreeSet<>();
    private final int MAX_NUMBER_OF_BANKNOTES = 100;

    public ATM() {

        for (Denominations denomination : Denominations.values()) {
            cells.add(new Cell(denomination));
        }
    }

    public double getTotalAmount() {
        var totalAmount = 0.0;
        for (Cell cell : cells) {
            totalAmount += cell.getNumberOfBanknotes() * cell.getDenominationValue();
        }
        return totalAmount;
    }

    public Map<Denominations, Integer> deposit(Map<Denominations, Integer> banknotes) {

        var rejectedBanknotes = new HashMap<Denominations, Integer>();

        for (Cell cell : cells) {

            var denomination = cell.getDenomination();
            if (banknotes.containsKey(denomination)) {

                var add = banknotes.get(denomination);
                var current = cell.getNumberOfBanknotes();
                var free = MAX_NUMBER_OF_BANKNOTES - current;
                var put = Math.min(free, add);
                var reject = add - put;

                cell.addBanknotes(put);

                if (reject > 0) {
                    rejectedBanknotes.put(denomination, reject);
                }
            }

        }

        return rejectedBanknotes;
    }

    public WithdrawResult withdraw(double amount) {

        var amountLeft = amount;
        var withdrawBanknotes = new HashMap<Denominations, Integer>();

        for (Cell cell : cells) {

            var denomination = cell.getDenomination();
            var denominationValue = cell.getDenominationValue();
            withdrawBanknotes.put(denomination, 0);

            for (int i = cell.getNumberOfBanknotes(); i > 0 && amountLeft >= denominationValue; i--) {
                withdrawBanknotes.put(denomination, withdrawBanknotes.get(denomination) + 1);
                amountLeft -= denominationValue;
            }
        }

        if (amountLeft == 0) {

            for (Cell cell : cells) {
                var denomination = cell.getDenomination();
                if (withdrawBanknotes.containsKey(denomination)) {
                    cell.removeBanknotes(withdrawBanknotes.get(denomination));
                }
            }
            return new WithdrawResult(true, "OK", withdrawBanknotes);

        } else {
            return new WithdrawResult(false, "Not enough money " + amountLeft, withdrawBanknotes);
        }

    }

    @Override
    public String toString() {
        return "TOTAL AMOUNT: " + this.getTotalAmount() + " " + this.cells.toString();
    }
}
