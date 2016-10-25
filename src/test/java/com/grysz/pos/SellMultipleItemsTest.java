package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellMultipleItemsTest {
    @Test
    public void scanProductAndDisplayPrice() {
        String barcode = "found";
        Price price = Price.euros(10);
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(price));

            oneOf(shoppingCart).put(price);
            oneOf(display).displayProductPrice(price);
        }});

        pos.productScanned(barcode);
    }

    @Test
    public void scanProductAndDisplayPriceNotFound() {
        String barcode = "not-found";
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(null));
            oneOf(display).displayPriceNotFound("not-found");
        }});

        pos.productScanned(barcode);
    }

    @Test
    public void finishScanningAndDisplayTotalWithGst() {
        Price grossPrice = Price.euros(10);
        Price netPrice = Price.euros(10, 50);
        context.checking(new Expectations() {{
            allowing(shoppingCart).getTotal();
            will(returnValue(grossPrice));

            oneOf(display).displayTotal(netPrice);
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
