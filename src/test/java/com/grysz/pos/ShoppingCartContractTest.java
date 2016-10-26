package com.grysz.pos;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class ShoppingCartContractTest {
    @Test
    public void initiallyIsEmpty() {
        ShoppingCart cart = createEmptyShoppingCart();

        assertThat(cart.getTotal(), equalTo(Price.cents(0)));
    }

    @Test
    public void afterPuttingProductTotalIsIncremented() {
        ShoppingCart cart = createShoppingCartWithProducts();
        Price initialTotal = cart.getTotal();

        cart.put(Price.cents(1011));

        assertThat(cart.getTotal(), equalTo(initialTotal.add(Price.cents(1011))));
    }

    protected abstract ShoppingCart createEmptyShoppingCart();

    protected abstract ShoppingCart createShoppingCartWithProducts();
}
