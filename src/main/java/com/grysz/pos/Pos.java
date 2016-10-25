package com.grysz.pos;

public class Pos {
    private static final int GST_PERCENTAGE = 5;

    private final ProductCatalog productCatalog;
    private final ShoppingCart shoppingCart;
    private final Display display;

    public Pos(ProductCatalog productCatalog, ShoppingCart shoppingCart, Display display) {
        this.productCatalog = productCatalog;
        this.shoppingCart = shoppingCart;
        this.display = display;
    }

    public void productScanned(String barcode) {
        Price price = productCatalog.find(barcode);
        if (price == null) {
            display.displayPriceNotFound(barcode);
        } else {
            display.displayProductPrice(price);
            shoppingCart.put(price);
        }
    }

    public void done() {
        Price grossTotal = shoppingCart.getTotal();
        Price gst = grossTotal.percent(GST_PERCENTAGE);
        Price netTotal = grossTotal.add(gst);
        display.displayTotal(netTotal);
    }
}
