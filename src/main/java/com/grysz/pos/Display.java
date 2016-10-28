package com.grysz.pos;

public interface Display {
    void displayProductPrice(Product product);

    void displayPriceNotFound(String notFound);

    void displayTotal(Price total);
}
