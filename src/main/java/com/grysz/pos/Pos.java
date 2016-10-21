package com.grysz.pos;

public class Pos {
    private final Display display;
    private final ProductCatalog productCatalog;
    private Price total = Price.euros(0);

    public Pos(ProductCatalog productCatalog, Display display) {
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

    public void itemScanned(String barcode) {
        Price price = productCatalog.find(barcode);
        if (price == null) {
            display.displayPriceNotFound(barcode);
        } else {
            display.displayProductPrice(price);
            total = total.add(price);
        }
    }

    public void done() {
        display.displayTotal(total);
    }
}
