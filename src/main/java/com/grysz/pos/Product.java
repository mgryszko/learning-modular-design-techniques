package com.grysz.pos;

public class Product {
    private Price price;
    private final boolean pstLevied;

    public Product(Price price, boolean pstLevied) {
        this.price = price;
        this.pstLevied = pstLevied;
    }

    public Price getPrice() {
        return price;
    }
}
