package com.grysz.pos;

public interface Display {
    void displayProductPrice(Price price);

    void displayProductPrice(Product product);

    void displayPriceNotFound(String notFound);

    void displayTotal(Price total);
}
