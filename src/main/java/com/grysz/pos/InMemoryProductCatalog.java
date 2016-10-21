package com.grysz.pos;

import java.util.Map;

public class InMemoryProductCatalog implements ProductCatalog {
    private final Map<String, Price> pricesByBarcode;

    public InMemoryProductCatalog(Map<String, Price> pricesByBarcode) {
        this.pricesByBarcode = pricesByBarcode;
    }

    @Override
    public Price find(String barcode) {
        return pricesByBarcode.get(barcode);
    }
}
