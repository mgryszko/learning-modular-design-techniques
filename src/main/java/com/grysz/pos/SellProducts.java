package com.grysz.pos;

public class SellProducts {
    private final ProductCatalog productCatalog;
    private final ShoppingCart shoppingCart;
    private final Display display;

    public SellProducts(ProductCatalog productCatalog, ShoppingCart shoppingCart, Display display) {
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
}
