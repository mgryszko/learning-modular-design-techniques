package com.grysz.pos;

public interface Display {
    void displayProductPrice(Price price);

    void displayPriceNotFound(String notFound);

    void displayTotal(Price total);

    void displayProductPrice(Price price, boolean pstLevied);
}
