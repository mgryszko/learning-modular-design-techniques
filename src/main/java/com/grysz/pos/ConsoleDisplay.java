package com.grysz.pos;

public class ConsoleDisplay implements Display {
    private final Console console;

    public ConsoleDisplay(Console console) {
        this.console = console;
    }

    @Override
    public void displayProductPrice(Product product) {
        console.println(formatProductPrice(product));
    }

    @Override
    public void displayPriceNotFound(String barcode) {
        console.println(formatPriceNotFound(barcode));
    }

    @Override
    public void displayTotal(Price total) {
        console.println(formatTotal(total));
    }

    private String formatProductPrice(Product product) {
        return String.format("%s %s", product.formatPrice(), formatTaxCode(product));
    }

    private String formatTaxCode(Product product) {
        String gstCode = "G";
        String pstCode = product.isPstLevied() ? "P" : "";
        return gstCode + pstCode;
    }

    private String formatPriceNotFound(String barcode) {
        return String.format("not found: %s", barcode);
    }

    private String formatTotal(Price total) {
        return String.format("total: %s", total.toCurrencyString());
    }
}
