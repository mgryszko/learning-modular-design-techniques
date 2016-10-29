package com.grysz.pos;

public class FinishSale {
    private final ShoppingCart shoppingCart;
    private final Display display;

    public FinishSale(ShoppingCart shoppingCart, Display display) {
        this.shoppingCart = shoppingCart;
        this.display = display;
    }

    public void done() {
        display.displayTotal(shoppingCart.getTotalWithTaxes());
    }
}
