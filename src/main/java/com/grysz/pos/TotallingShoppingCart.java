package com.grysz.pos;

import java.util.ArrayList;
import java.util.Collection;

class TotallingShoppingCart implements ShoppingCart {
    private Price total = Price.cents(0);
    private Collection<Product> products = new ArrayList<>();

    @Override
    public void put(Price price) {
        total = total.add(price);
    }

    @Override
    public void put(Product product) {
        products.add(product);
        total = total.add(product.getPrice());
    }

    @Override
    public Price getTotal() {
        return total;
    }

    @Override
    public Price getTotalWithTaxes() {
        return products.stream()
                .map(Product::netPrice)
                .reduce(Price.cents(0), Price::add);
    }
}
