package com.grysz.pos;

public interface ProductCatalog {
    Price find(String barcode);
}
