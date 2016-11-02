package com.grysz.pos;

import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class ShoppingCartContractTest {
    @Test
    public void initiallyIsEmpty() {
        ShoppingCart cart = createEmptyShoppingCart();

        assertThat(cart.getProducts(), empty());
    }

    @Test
    public void afterPuttingProduct() {
        ShoppingCart cart = createEmptyShoppingCart();
        Product product = new Product(Price.cents(500), false);

        cart.put(product);
        cart.put(product);

        // TODO finish this test

        assertThat(cart.getProducts(),  containsInAnyOrder(product, product));
    }

    protected abstract ShoppingCart createEmptyShoppingCart();
}
