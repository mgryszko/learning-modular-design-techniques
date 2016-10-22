package com.grysz.pos;

public class TotallingShoppingCartTest extends ShoppingCartContractTest {
    protected ShoppingCart createEmptyShoppingCart() {
        return new TotallingShoppingCart();
    }

    protected ShoppingCart createShoppingCartWithProducts() {
        ShoppingCart cart = createEmptyShoppingCart();
        cart.put(Price.euros(5, 1));
        return cart;
    }
}
