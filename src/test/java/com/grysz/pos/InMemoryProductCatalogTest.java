package com.grysz.pos;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductCatalogTest extends ProductCatalogContractTest {
    protected ProductCatalog createProductCatalogWith(String barcode, Price price) {
        return new InMemoryProductCatalog(pricesByBarcodeWith(barcode, price));
    }

    protected ProductCatalog createProductCatalogWithout(String barcode) {
        return new InMemoryProductCatalog(pricesByBarcodeWithout(barcode));
    }

    private Map<String, Price> pricesByBarcodeWith(String barcode, Price price) {
        Map<String, Price> pricesByBarcodes = new HashMap<>();
        pricesByBarcodes.put(barcode, price);
        pricesByBarcodes.put("other-than-" + barcode, price);
        pricesByBarcodes.put("yet-other-than-" + barcode, price);
        return pricesByBarcodes;
    }

    private Map<String, Price> pricesByBarcodeWithout(String barcode) {
        Map<String, Price> pricesByBarcodes = new HashMap<>();
        pricesByBarcodes.put("other-than-" + barcode, Price.cents(100));
        pricesByBarcodes.put("yet-other-than-" + barcode, Price.cents(200));
        return pricesByBarcodes;
    }
}
