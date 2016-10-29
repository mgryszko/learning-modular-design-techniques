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

    public Price netPrice() {
        return price.add(gst()).add(pst());
    }

    private Price gst() {
        return price.percent(5);
    }

    private Price pst() {
        return pstLevied ? price.percent(8) : Price.cents(0);
    }
}
