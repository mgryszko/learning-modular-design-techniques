package com.grysz.pos;

import org.jmock.Expectations;
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
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode1);
            will(returnValue(price1));
            allowing(productCatalog).find(barcode2);
            will(returnValue(price2));

            oneOf(display).displayProductPrice(price1);
            oneOf(display).displayProductPrice(price2);
            oneOf(display).displayTotal(Price.euros(30));
        }});

        useCase.itemScanned(barcode1);
        useCase.itemScanned(barcode2);
        useCase.done();
    }

    @Test
    public void scanItemAndDisplayPriceNotFound() {
        String barcode = "not-found";
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(null));
            oneOf(display).displayPriceNotFound("not-found");
        }});

        useCase.itemScanned(barcode);
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ProductCatalog productCatalog = context.mock(ProductCatalog.class);
    private Pos useCase = new Pos(productCatalog, display);
}
