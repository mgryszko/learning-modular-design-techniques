package com.grysz.pos;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Price {
    private static final BigDecimal CENTS_IN_EUR = BigDecimal.valueOf(100);

    public static Price cents(int cents) {
        return new Price(cents);
    }

    private final BigDecimal amount;

    private Price(int cents) {
        amount = BigDecimal.valueOf(cents).divide(CENTS_IN_EUR);
    }

    private Price(BigDecimal amount) {
        this.amount = new BigDecimal(String.valueOf(amount));
    }

    public Price add(Price that) {
        return new Price(this.amount.add(that.amount));
    }

    public Price percent(int percent) {
        return new Price(amount.multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100)));
    }

    @Override
    public String toString() {
        return new DecimalFormat("####0.00 €").format(amount);
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
}
