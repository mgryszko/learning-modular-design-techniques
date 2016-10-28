package com.grysz.pos;

import java.util.Optional;

public interface ProductCatalog {
    Price find(String barcode);

    Optional<Product> findProduct(String barcode);
}
