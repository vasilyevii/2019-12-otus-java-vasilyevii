package com.vasilyevii;

public class Cell implements Comparable<Cell> {

    private final Denominations denomination;
    private final double denominationValue;
    private int numberOfBanknotes = 0;

    public Cell(Denominations denomination) {
        this.denomination = denomination;
        this.denominationValue = Denominations.getDenominationValue(denomination);
    }

    public void addBanknotes(int number) {
        this.numberOfBanknotes += number;
    }

    public void removeBanknotes(int number) {
        this.numberOfBanknotes -= number;
    }

    public Denominations getDenomination() {
        return denomination;
    }

    public double getDenominationValue() {
        return denominationValue;
    }

    public int getNumberOfBanknotes() {
        return numberOfBanknotes;
    }

    @Override
    public int compareTo(Cell o) {
        return (int) (o.getDenominationValue() - this.getDenominationValue());
    }

    @Override
    public String toString() {
        return denomination + ": " + numberOfBanknotes;
    }
}
