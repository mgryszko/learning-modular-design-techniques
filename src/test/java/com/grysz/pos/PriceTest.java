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
    @Parameters({"   100 |    1.00 €",
                 "   110 |    1.10 €",
                 "   123 |    1.23 €",
                 "  1023 |   10.23 €",
                 "    12 |    0.12 €",
                 "100001 | 1000.01 €"})
    public void stringRepresentation(int cents, String formattedPrice) {
        Price price = Price.cents(cents);
        assertThat(price.toString(), equalTo(formattedPrice));
    }
}
