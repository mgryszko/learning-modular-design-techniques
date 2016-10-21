package com.grysz.pos;

public interface Display {
    void displayTotal(Price total);

    void displayPriceNotFound(String notFound);

    void displayProductPrice(Price price);
}
