package com.grysz.pos;

public class FinishSale {
    private static final int GST_PERCENTAGE = 5;

    private final ShoppingCart shoppingCart;
    private final Display display;

    public FinishSale(ShoppingCart shoppingCart, Display display) {
        this.shoppingCart = shoppingCart;
        this.display = display;
    }

    public void done() {
        Price grossTotal = shoppingCart.getTotal();
        Price gst = grossTotal.percent(GST_PERCENTAGE);
        Price netTotal = grossTotal.add(gst);
        display.displayTotal(netTotal);
    }
}
