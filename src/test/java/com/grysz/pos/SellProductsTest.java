package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellProductsTest {
    @Test
    public void scanProductAndDisplayPriceWithoutPst() {
        String barcode = "found";
        Price price = Price.cents(1000);
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(price));

            oneOf(display).displayProductPrice(price, false);
            oneOf(shoppingCart).put(price);
        }});

        sellProducts.productScanned(barcode);
    }

    @Test
    public void scanProductAndDisplayPriceWithPst() {
        String barcode = "found-with-pst";
        Price price = Price.cents(1000);
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(price));

            oneOf(display).displayProductPrice(price, true);
            oneOf(shoppingCart).put(price);
        }});

        sellProducts.productScanned(barcode);
    }

    @Test
    public void scanProductAndDisplayPriceNotFound() {
        String barcode = "not-found";
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(null));
            oneOf(display).displayPriceNotFound("not-found");
        }});

        sellProducts.productScanned(barcode);
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ProductCatalog productCatalog = context.mock(ProductCatalog.class);
    private ShoppingCart shoppingCart = context.mock(ShoppingCart.class);
    private SellProducts sellProducts = new SellProducts(productCatalog, shoppingCart, display);
}
