package com.grysz.pos;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TotallingShoppingCartTest extends ShoppingCartContractTest {
    @Override
    protected ShoppingCart createEmptyShoppingCart() {
        return new TotallingShoppingCart();
    }

    @Override
    protected ShoppingCart createShoppingCartWithTotalAt(Price total) {
        ShoppingCart cart = createEmptyShoppingCart();
        cart.put(total);
        return cart;
    }

    @Test
    public void afterPuttingProductsTotalWithTaxesIsCalculated() {
        Product productWithoutPst = new Product(Price.cents(500), false);
        Product productWithPst = new Product(Price.cents(600), true);
        ShoppingCart cart = new TotallingShoppingCart();
        cart.put(productWithoutPst);
        cart.put(productWithPst);

        Price productWithoutPstNet = Price.cents(525);
        Price productWithPstNet = Price.cents(678);
        assertThat(cart.getTotalWithTaxes(), equalTo(productWithoutPstNet.add(productWithPstNet)));
    }
}
