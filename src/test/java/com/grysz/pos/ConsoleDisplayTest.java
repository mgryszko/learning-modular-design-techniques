package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ConsoleDisplayTest {
    @Test
    public void formattedTotal() {
        context.checking(new Expectations() {{
            oneOf(console).println("total: 10.00 €"); 
        }});

        display.displayTotal(Price.euros(10));
    }

    @Test
    public void formattedTotalWithDecimals() {
        context.checking(new Expectations() {{
            oneOf(console).println("total: 10.13 €");
        }});

        display.displayTotal(Price.euros(10, 13));
    }

    @Test
    public void priceNotFoundForBarcode() {
        String barcode = "not-found";
        context.checking(new Expectations() {{
            oneOf(console).println("not found: not-found");
        }});

        display.displayPriceNotFound(barcode);
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    
    private Console console = context.mock(Console.class);
    private ConsoleDisplay display = new ConsoleDisplay(console);
}
