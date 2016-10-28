package com.grysz.pos;

public interface Display {
    void displayProductPrice(Product product);

    void displayPriceNotFound(String barcode);

    void displayTotal(Price total);
}
