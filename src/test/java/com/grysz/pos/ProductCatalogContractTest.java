package com.grysz.pos;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class ProductCatalogContractTest {
    private String foundBarcode = "found";
    private String notFoundBarcode = "not-found";
    private Price foundPrice = Price.euros(10);

    @Test
    public void priceFound() {
        ProductCatalog productCatalog = createProductCatalogWith(foundBarcode, foundPrice);

        assertThat(productCatalog.find(foundBarcode), equalTo(foundPrice));
    }

    @Test
    public void priceNotFound() {
        ProductCatalog productCatalog = createProductCatalogWithout(notFoundBarcode);

        assertThat(productCatalog.find(notFoundBarcode), equalTo(null));
    }

    protected abstract ProductCatalog createProductCatalogWith(String barcode, Price price);

    protected abstract ProductCatalog createProductCatalogWithout(String barcode);
}
