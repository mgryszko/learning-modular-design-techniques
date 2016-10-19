package com.grysz.pos;

import java.util.Map;

public class SellOneItem {
    private final Map<String, Price> pricesByBarcode;
    private final Display display;

    public SellOneItem(Map<String, Price> pricesByBarcode, Display display) {
        this.pricesByBarcode = pricesByBarcode;
        this.display = display;
    }

    public void itemScanned(String barcode) {
        Price price = pricesByBarcode.get(barcode);
        if (price == null) {
            display.displayPriceNotFound(String.format("not found: %s", barcode));
        } else {
            display.displayTotal(String.format("total: %s", price));
        }
    }
}
