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
            display.displayPriceNotFound(barcode);
        } else {
            display.displayTotal(price);
        }
    }
}
