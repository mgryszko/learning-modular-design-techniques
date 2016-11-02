package com.grysz.pos;

import java.util.Collection;

class CanadianTaxCalculator implements TaxCalculator {
    @Override
    public Price calcuteNetTotal(Collection<Product> products) {
        return products.stream()
            .map(Product::netPrice)
            .reduce(Price.cents(0), Price::add);
    }
}
