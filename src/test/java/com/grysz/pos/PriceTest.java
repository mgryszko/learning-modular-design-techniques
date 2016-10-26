package com.grysz.pos;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PriceTest {
    @Test
    @Parameters({
        "1010 | 50.5",
        "1009 | 50.5",
        "1008 | 50.4",
        "1007 | 50.4"})
    public void isRoundedUpWhenMultiplyingByPercentage(int cents, double percentCents) {
        assertThat(Price.cents(cents).percent(5), equalTo(Price.cents(percentCents)));
    }

    @Test
    public void addPricesWithoutCentDecimals() {
        assertThat(Price.cents(1).add(Price.cents(2)), equalTo(Price.cents(3)));
    }

    @Test
    @Parameters({
        "0.1  | 0.2  | 0.3",
        "0.15 | 0.25 | 0.5"})
    public void addPricesWithCentDecimals(double addend, double augend, double sum) {
        assertThat(Price.cents(addend).add(Price.cents(augend)), equalTo(Price.cents(sum)));
    }

    @Test
    public void equalityWithoutCentDecimals() {
        assertThat(Price.cents(123), equalTo(Price.cents(123)));
    }

    @Test
    @Parameters({"0.05", "0.09", "0.11", "0.14"})
    public void equalityWithCentDecimals(double cents) {
        assertThat(Price.cents(cents), equalTo(Price.cents(0.1)));
    }

    @Test
    @Parameters({
        "   100 |    1.00 €",
        "   110 |    1.10 €",
        "   123 |    1.23 €",
        "  1023 |   10.23 €",
        "    12 |    0.12 €",
        "100001 | 1000.01 €"})
    public void moneyRepresentation(int cents, String formattedPrice) {
        assertThat(Price.cents(cents).toCurrencyString(), equalTo(formattedPrice));
    }
}
