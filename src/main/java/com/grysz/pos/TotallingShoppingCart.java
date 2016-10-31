package com.grysz.pos;

import java.util.ArrayList;
import java.util.Collection;

class TotallingShoppingCart implements ShoppingCart {
    private Collection<Product> products = new ArrayList<>();

    @Override
    public void put(Product product) {
        products.add(product);
    }

    @Override
    public Price getTotalWithTaxes() {
        return products.stream()
                .map(Product::netPrice)
                .reduce(Price.cents(0), Price::add);
    }
}
