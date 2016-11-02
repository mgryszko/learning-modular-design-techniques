package com.grysz.pos;

import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class CanadianTaxCalculatorTest {
    @Test
    public void netTotal() {
        Product productWithoutPst = new Product(Price.cents(500), false);
        Product productWithPst = new Product(Price.cents(600), true);
        Collection<Product> products = asList(productWithoutPst, productWithPst);

        Price netTotal = taxCalculator.calcuteNetTotal(products);

        Price productWithoutPstNet = Price.cents(525);
        Price productWithPstNet = Price.cents(678);
        assertThat(netTotal, equalTo(productWithoutPstNet.add(productWithPstNet)));
    }

    private TaxCalculator taxCalculator = new CanadianTaxCalculator();
}
