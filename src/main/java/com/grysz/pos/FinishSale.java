package com.grysz.pos;

public class FinishSale {
    private final ShoppingCart shoppingCart;
    private final Display display;
    private final TaxCalculator taxCalculator;

    public FinishSale(ShoppingCart shoppingCart, Display display, TaxCalculator taxCalculator) {
        this.shoppingCart = shoppingCart;
        this.display = display;
        this.taxCalculator = taxCalculator;
    }

    public void done() {
        Price netTotal = taxCalculator.calcuteNetTotal(shoppingCart.getProducts());
        display.displayTotal(netTotal);
    }
}
