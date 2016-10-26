package com.grysz.pos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Price {
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private static final BigDecimal CENTS_IN_EUR = HUNDRED;

    public static Price cents(int cents) {
        return new Price(new BigDecimal(BigInteger.valueOf(cents), 2));
    }

    // This constructor is only for the implementation testing
    static Price cents(double cents) {
        return new Price(BigDecimal.valueOf(cents).divide(CENTS_IN_EUR));
    }

    private final BigDecimal amount;

    private Price(BigDecimal amount) {
        this.amount = new BigDecimal(String.valueOf(amount)).setScale(3, RoundingMode.HALF_UP);
    }

    public Price add(Price that) {
        return new Price(this.amount.add(that.amount));
    }

    public Price percent(int percent) {
        BigDecimal percentAsDecimal = BigDecimal.valueOf(percent).divide(HUNDRED);
        return new Price(amount.multiply(percentAsDecimal));
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
