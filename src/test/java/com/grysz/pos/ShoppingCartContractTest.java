package com.grysz.pos;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public abstract class ShoppingCartContractTest {
    @Test
    public void initiallyIsEmpty() {
        ShoppingCart cart = createEmptyShoppingCart();

        assertThat(cart.getTotalWithTaxes(), equalTo(Price.cents(0)));
    }

    @Test
    public void afterPuttingProductTotalWithTaxesIsIncremented() {
        ShoppingCart cart = createEmptyShoppingCart();
        Product productWithoutPst = new Product(Price.cents(500), false);

        cart.put(productWithoutPst);

        Price productWithoutPstNet = Price.cents(525);
        assertThat(cart.getTotalWithTaxes(), equalTo(productWithoutPstNet ));

        Product productWithPst = new Product(Price.cents(600), true);
        Price productWithPstNet = Price.cents(678);
        cart.put(productWithPst);

        assertThat(cart.getTotalWithTaxes(), equalTo(productWithoutPstNet.add(productWithPstNet)));
    }

    protected abstract ShoppingCart createEmptyShoppingCart();
}
