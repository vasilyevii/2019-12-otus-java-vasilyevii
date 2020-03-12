package com.vasilyevii;

public enum Denominations {

    FIFTY,
    ONE_HUNDRED,
    TWO_HUNDRED,
    FIVE_HUNDRED,
    ONE_THOUSAND,
    TWO_THOUSAND,
    FIVE_THOUSAND;

    public static double getDenominationValue(Denominations denomination) {

        switch (denomination) {
            case FIFTY: return 50;
            case ONE_HUNDRED: return 100;
            case TWO_HUNDRED: return 200;
            case FIVE_HUNDRED: return 500;
            case ONE_THOUSAND: return 1000;
            case TWO_THOUSAND: return 2000;
            case FIVE_THOUSAND: return 5000;
            default: return 0;
        }
    }

}
