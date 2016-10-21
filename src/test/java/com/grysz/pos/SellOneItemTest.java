package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemTest {
    @Test
    public void displayProductPriceAndTotalWhenPriceFound() {
        String barcode = "found";
        Price price = Price.euros(10);
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(price));

            oneOf(display).displayProductPrice(price);
            oneOf(display).displayTotal(price);
        }});

        useCase.itemScannedAndDone(barcode);
    }

    @Test
    public void displayPriceNotFound() {
        String barcode = "not-found";
        context.checking(new Expectations() {{
            allowing(productCatalog).find(barcode);
            will(returnValue(null));
            oneOf(display).displayPriceNotFound("not-found");
        }});

        useCase.itemScannedAndDone(barcode);
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ProductCatalog productCatalog = context.mock(ProductCatalog.class);
    private SellOneItem useCase = new SellOneItem(productCatalog, display);
}
