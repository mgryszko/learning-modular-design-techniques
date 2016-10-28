package com.grysz.pos;

public interface Display {
    void displayProductPrice(Price price);

    void displayProductPrice(Price price, boolean pstLevied);

    void displayProductPrice(Product product);

    void displayPriceNotFound(String notFound);

    void displayTotal(Price total);
}
