package com.grysz.pos;

public interface ShoppingCart {
    void put(Product product);

    Price getTotalWithTaxes();
}
