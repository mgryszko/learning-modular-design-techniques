package com.grysz.pos;

public class Pos {
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
        display.displayTotal(shoppingCart.getTotal());
    }
}
