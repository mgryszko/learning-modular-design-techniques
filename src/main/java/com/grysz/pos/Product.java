package com.grysz.pos;

public class Product {
    private Price price;
    private final boolean pstLevied;

    public Product(Price price, boolean pstLevied) {
        this.price = price;
        this.pstLevied = pstLevied;
    }

    public String formatPrice() {
        return price.toCurrencyString();
    }

    public Price getPrice() {
        return price;
    }

    public boolean isPstLevied() {
        return pstLevied;
    }
}
