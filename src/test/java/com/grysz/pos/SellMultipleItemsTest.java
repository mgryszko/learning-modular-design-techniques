package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellMultipleItemsTest {
    @Test
    public void scanMultipleItemsAndDisplayTotal() {
        String barcode1 = "12345";
        String barcode2 = "23456";
        Price price1 = Price.euros(10);
        Price price2 = Price.euros(20);
        final States shoppingCartStates = context.states("shoppingCart").startsAs("empty");
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode1);
            will(returnValue(price1));
            oneOf(shoppingCart).put(price1);

            allowing(productCatalog).find(barcode2);
            will(returnValue(price2));
            oneOf(shoppingCart).put(price2);
            then(shoppingCartStates.is("full"));

            allowing(shoppingCart).getTotal();
            will(returnValue(Price.euros(30)));
            when(shoppingCartStates.is("full"));

            oneOf(display).displayProductPrice(price1);
            oneOf(display).displayProductPrice(price2);
            oneOf(display).displayTotal(Price.euros(30));
        }});

        pos.itemScanned(barcode1);
        pos.itemScanned(barcode2);
        pos.done();
    }

    @Test
    public void scanItemAndDisplayPriceNotFound() {
        String barcode = "not-found";
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(null));
            oneOf(display).displayPriceNotFound("not-found");
        }});

        pos.itemScanned(barcode);
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ProductCatalog productCatalog = context.mock(ProductCatalog.class);
    private ShoppingCart shoppingCart = context.mock(ShoppingCart.class);
    private Pos pos = new Pos(productCatalog, shoppingCart, display);
}
