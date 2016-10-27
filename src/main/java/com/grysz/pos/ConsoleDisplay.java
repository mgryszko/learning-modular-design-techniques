package com.grysz.pos;

public class ConsoleDisplay implements Display {
    private final Console console;

    public ConsoleDisplay(Console console) {
        this.console = console;
    }

    @Override
    public void displayProductPrice(Price price) {
        console.println(formatProductPrice(price));
    }

    @Override
    public void displayPriceNotFound(String barcode) {
        console.println(formatPriceNotFound(barcode));
    }

    @Override
    public void displayTotal(Price total) {
        console.println(formatTotal(total));
    }

    private String formatProductPrice(Price price) {
        return String.format("%s", price.toCurrencyString());
    }

    private String formatPriceNotFound(String barcode) {
        return String.format("not found: %s", barcode);
    }

    private String formatTotal(Price total) {
        return String.format("total: %s", total.toCurrencyString());
    }
}
