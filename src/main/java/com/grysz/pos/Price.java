package com.grysz.pos;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Price {
    private static final int CENTS_IN_EUR = 100;

    public static Price euros(int euros) {
        return new Price(euros, 0);
    }

    public static Price euros(int euros, int cents) {
        return new Price(euros, cents);
    }

    private final BigDecimal amount;

    private Price(int euros, int cents) {
        amount = new BigDecimal(euros).add(new BigDecimal(cents).divide(new BigDecimal(CENTS_IN_EUR)));
    }

    private Price(BigDecimal amount) {
        this.amount = new BigDecimal(String.valueOf(amount));
    }

    public Price add(Price that) {
        return new Price(this.amount.add(that.amount));
    }

    @Override
    public String toString() {
        return new DecimalFormat("#####.00 €").format(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return amount.toPlainString().equals(((Price) o).amount.toPlainString());
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    public Price percent(int percent) {
        return new Price(amount.multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100)));
    }
}
