package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;

public class FinishSaleTest {

    @Test
    public void finishScanningAndDisplayTotalWithGst() {
        Price netPrice = Price.cents(1000);
        Collection<Product> products = asList(new Product(Price.cents(500), false), new Product(Price.cents(600), true));
        context.checking(new Expectations() {{
            allowing(shoppingCart).getProducts();
            will(returnValue(products));

            allowing(taxCalculator).calcuteNetTotal(products);
            will(returnValue(netPrice));

            oneOf(display).displayTotal(netPrice);
        }});

        finishSale.done();
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ShoppingCart shoppingCart = context.mock(ShoppingCart.class);
    private TaxCalculator taxCalculator = context.mock(TaxCalculator.class);
    private FinishSale finishSale = new FinishSale(shoppingCart, display, taxCalculator);
}
