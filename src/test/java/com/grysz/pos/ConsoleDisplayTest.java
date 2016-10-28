package com.grysz.pos;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ConsoleDisplayTest {
    @Test
    @Parameters({
        "1013 | false | 10.13 € G",
        "1013 | true  | 10.13 € GP"
    })
    public void formattedProductPrice(int cents, boolean pstLevied, String formattedPrice) {
        context.checking(new Expectations() {{
            oneOf(console).println(formattedPrice);
        }});

        display.displayProductPrice(new Product(Price.cents(cents), pstLevied));
    }

    @Test
    public void priceNotFoundForBarcode() {
        String barcode = "not-found";
        context.checking(new Expectations() {{
            oneOf(console).println("not found: not-found");
        }});

        display.displayPriceNotFound(barcode);
    }

    @Test
    public void formattedTotal() {
        context.checking(new Expectations() {{
            oneOf(console).println("total: 10.00 €");
        }});

        display.displayTotal(Price.cents(1000));
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    
    private Console console = context.mock(Console.class);
    private Display display = new ConsoleDisplay(console);
}
