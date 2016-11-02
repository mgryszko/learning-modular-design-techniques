package com.grysz.pos;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

class TotallingShoppingCart implements ShoppingCart {
    private Collection<Product> products = new ArrayList<>();

    @Override
    public void put(Product product) {
        products.add(product);
    }

    @Override
    public Collection<Product> getProducts() {
        return unmodifiableCollection(products);
    }
}
