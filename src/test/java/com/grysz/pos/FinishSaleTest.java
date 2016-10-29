package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class FinishSaleTest {
    @Test
    public void finishScanningAndDisplayTotalWithGst() {
        Price price = Price.cents(1000);
        context.checking(new Expectations() {{
            allowing(shoppingCart).getTotalWithTaxes();
            will(returnValue(price));

            oneOf(display).displayTotal(price);
        }});

        finishSale.done();
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ShoppingCart shoppingCart = context.mock(ShoppingCart.class);
    private FinishSale finishSale = new FinishSale(shoppingCart, display);
}
