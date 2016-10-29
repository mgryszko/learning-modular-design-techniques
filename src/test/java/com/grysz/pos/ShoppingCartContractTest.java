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
        Price initialTotal = Price.cents(501);
        ShoppingCart cart = createShoppingCartWithTotalAt(initialTotal);

        cart.put(new Product(Price.cents(1011), false));

        assertThat(cart.getTotal(), equalTo(Price.cents(1512)));
    }

    protected abstract ShoppingCart createEmptyShoppingCart();

    protected abstract ShoppingCart createShoppingCartWithTotalAt(Price total);
}
