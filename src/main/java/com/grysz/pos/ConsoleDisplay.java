package com.grysz.pos;

public class ConsoleDisplay implements Display {
    private final Console console;

    public ConsoleDisplay(Console console) {
        this.console = console;
    }

    @Override
    public void displayTotal(Price total) {
        console.println(formatTotal(total));
    }

    @Override
    public void displayPriceNotFound(String barcode) {
        console.println(formatPriceNotFound(barcode));
    }

    @Override
    public void displayProductPrice(Price price) {
        throw new RuntimeException("not implemented yet");
    }

    private String formatTotal(Price total) {
        return String.format("total: %s", total.toCurrencyString());
    }

    private String formatPriceNotFound(String barcode) {
        return String.format("not found: %s", barcode);
    }
}
