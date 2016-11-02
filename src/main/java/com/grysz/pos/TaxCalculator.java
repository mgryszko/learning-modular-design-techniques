package com.grysz.pos;

import java.util.Collection;

public interface TaxCalculator {
    Price calcuteNetTotal(Collection<Product> products);
}
