package com.grysz.pos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Objects;

public class Price {
    private static final int CENTS_IN_EUR = 100;

    public static Price euros(int euros) {
        return Price.euros(euros, 0);
    }

    public static Price euros(int euros, int cents) {
        return new Price(euros, cents);
    }

    private final int valueInCents;

    private Price(int euros, int cents) {
        valueInCents = euros * CENTS_IN_EUR + cents;
    }

    @Override
    public String toString() {
        BigDecimal valueToFormat = new BigDecimal(BigInteger.valueOf(valueInCents), 2);
        return new DecimalFormat("#####.00 €").format(valueToFormat);
    }
}
