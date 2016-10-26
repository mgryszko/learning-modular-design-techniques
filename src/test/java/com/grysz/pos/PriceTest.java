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
    public void addPricesWithCentDecimals() {
        assertThat(Price.cents(0.1).add(Price.cents(0.2)), equalTo(Price.cents(0.3)));
        assertThat(Price.cents(0.15).add(Price.cents(0.25)), equalTo(Price.cents(0.5)));
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
        Price price = Price.cents(cents);
        assertThat(price.toCurrencyString(), equalTo(formattedPrice));
    }
}
