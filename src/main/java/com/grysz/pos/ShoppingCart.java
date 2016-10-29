package com.grysz.pos;

public interface ShoppingCart {
    void put(Price price);

    void put(Product product);

    Price getTotal();

    Price getTotalWithTaxes();
}
