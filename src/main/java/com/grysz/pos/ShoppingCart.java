package com.grysz.pos;

public interface ShoppingCart {
    void put(Price price);

    Price getTotal();
}
