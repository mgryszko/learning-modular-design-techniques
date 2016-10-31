package com.grysz.pos;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Optional;

public class SellProductsTest {
    @Test
    public void scanProductAndDisplayPrice() {
        String barcode = "found";
        Product product = anyProduct();
        context.checking(new Expectations() {{
            allowing(productCatalog).findProduct(barcode);
            will(returnValue(Optional.of(product)));

            oneOf(display).displayProductPrice(product);
            oneOf(shoppingCart).put(product);
        }});

        sellProducts.productScanned(barcode);
    }

    @Test
    public void scanProductAndDisplayPriceNotFound() {
        String barcode = "not-found";
        context.checking(new Expectations() {{
            allowing(productCatalog).findProduct(barcode);
            will(returnValue(Optional.empty()));
            oneOf(display).displayPriceNotFound("not-found");
        }});

        sellProducts.productScanned(barcode);
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Display display = context.mock(Display.class);
    private ProductCatalog productCatalog = context.mock(ProductCatalog.class);
    private ShoppingCart shoppingCart = context.mock(ShoppingCart.class);
    private SellProducts sellProducts = new SellProducts(productCatalog, shoppingCart, display);

    private Product anyProduct() {
        boolean anyPstLevied = false;
        Price anyPrice = Price.cents(1000);
        return new Product(anyPrice, anyPstLevied);
    }
}
