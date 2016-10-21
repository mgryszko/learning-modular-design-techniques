package com.grysz.pos;

public class SellOneItem {
    private final Display display;
    private final ProductCatalog productCatalog;

    public SellOneItem(ProductCatalog productCatalog, Display display) {
        this.productCatalog = productCatalog;
        this.display = display;
    }

    public void itemScannedAndDone(String barcode) {
        Price price = productCatalog.find(barcode);
        if (price == null) {
            display.displayPriceNotFound(barcode);
        } else {
            display.displayProductPrice(price);
            display.displayTotal(price);
        }
    }
}
