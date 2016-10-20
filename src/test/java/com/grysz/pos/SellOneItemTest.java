package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    public void displayTotalWhenRoundPriceFound() {
        String barcode = "found";
        Price price = Price.euros(10);
        Map<String, Price> pricesByBarcode = createPricesByBarcodesWith(barcode, price);
        context.checking(new Expectations() {{
            oneOf(display).displayTotal(price);
        }});

        new SellOneItem(pricesByBarcode, display).itemScanned(barcode);
    }

    @Test
    public void displayPriceNotFound() {
        String barcode = "not-found";
        Map<String, Price> pricesByBarcode = createPriceCatalogWithout(barcode);
        context.checking(new Expectations() {{
            oneOf(display).displayPriceNotFound("not-found");
        }});

        new SellOneItem(pricesByBarcode, display).itemScanned(barcode);
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Map<String, Price> createPricesByBarcodesWith(String barcode, Price price) {
        Map<String, Price> pricesByBarcodes = new HashMap<>();
        pricesByBarcodes.put(barcode, price);
        pricesByBarcodes.put("other-than-" + barcode, price);
        pricesByBarcodes.put("yet-other-than-" + barcode, price);
        return pricesByBarcodes;
    }

    private Map<String, Price> createPriceCatalogWithout(String barcode) {
        Map<String, Price> pricesByBarcodes = new HashMap<>();
        pricesByBarcodes.put("other-than-" + barcode, Price.euros(1));
        pricesByBarcodes.put("yet-other-than-" + barcode, Price.euros(2));
        return pricesByBarcodes;
    }

    private Display display = context.mock(Display.class);
}
