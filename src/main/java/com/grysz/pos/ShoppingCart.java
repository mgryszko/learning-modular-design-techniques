package com.grysz.pos;

import java.util.Collection;

public interface ShoppingCart {
    void put(Product product);

    Collection<Product> getProducts();
}
