package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.States;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellMultipleItemsTest {
    @Test
    public void scanProductAndDisplayPrice() {
        String barcode = "12345";
        Price price = Price.euros(10);
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(price));

            oneOf(shoppingCart).put(price);
            oneOf(display).displayProductPrice(price);
        }});

        pos.itemScanned(barcode);
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

    @Test
    public void finishScanningAndDisplayTotal() {
        Price price = Price.euros(10);
        context.checking(new Expectations() {{
            allowing(shoppingCart).getTotal();
            will(returnValue(price));

            oneOf(display).displayTotal(price);
        }});

        pos.done();
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ProductCatalog productCatalog = context.mock(ProductCatalog.class);
    private ShoppingCart shoppingCart = context.mock(ShoppingCart.class);
    private Pos pos = new Pos(productCatalog, shoppingCart, display);
}
