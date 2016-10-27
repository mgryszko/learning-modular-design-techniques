package com.grysz.pos;

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
}
