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
    @Parameters({"   1 | 00 |     1.00 €",
                 "   1 | 10 |     1.10 €",
                 "   1 | 23 |     1.23 €",
                 "  10 | 23 |    10.23 €",
                 "   0 | 12 |     0.12 €",
                 "1000 |  1 |  1000.01 €"})
    public void stringRepresentation(int euros, int cents, String formattedPrice) {
        Price price = Price.euros(euros, cents);
        assertThat(price.toString(), equalTo(formattedPrice));
    }
}
