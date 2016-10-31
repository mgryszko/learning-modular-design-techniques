package com.grysz.pos;

public class TotallingShoppingCartTest extends ShoppingCartContractTest {
    @Override
    protected ShoppingCart createEmptyShoppingCart() {
        return new TotallingShoppingCart();
    }
}
