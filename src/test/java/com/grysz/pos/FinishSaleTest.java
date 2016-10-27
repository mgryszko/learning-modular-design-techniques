package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class FinishSaleTest {
    @Test
    public void finishScanningAndDisplayTotalWithGst() {
        Price grossPrice = Price.cents(1000);
        Price netPrice = Price.cents(1050);
        context.checking(new Expectations() {{
            allowing(shoppingCart).getTotal();
            will(returnValue(grossPrice));

            oneOf(display).displayTotal(netPrice);
        }});

        finishSale.done();
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ShoppingCart shoppingCart = context.mock(ShoppingCart.class);
    private FinishSale finishSale = new FinishSale(shoppingCart, display);
}
