package com.grysz.pos;

import java.util.Optional;

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
        Optional<Product> product = productCatalog.findProduct(barcode);
        if (product.isPresent()) {
            display.displayProductPrice(product.get());
            shoppingCart.put(product.get().getPrice());
        } else {
            display.displayPriceNotFound(barcode);
        }
    }
}
