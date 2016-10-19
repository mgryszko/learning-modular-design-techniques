import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    public void displayTotalWhenRoundPriceFound() {
        String barcode = "found";
        pricesByBarcode = createPricesByBarcodesWith(barcode, Price.euros(10));
        context.checking(new Expectations() {{
            oneOf(display).displayTotal("total: 10.00 €");
        }});

        itemScanned(barcode);
    }

    @Test
    public void displayTotalWhenPriceWithDecimalsFound() {
        String barcode = "found";
        pricesByBarcode = createPricesByBarcodesWith(barcode, Price.euros(10, 13));
        context.checking(new Expectations() {{
            oneOf(display).displayTotal("total: 10.13 €");
        }});

        itemScanned(barcode);
    }

    @Test
    public void displayPriceNotFound() {
        String barcode = "not-found";
        pricesByBarcode = createPriceCatalogWithout(barcode);
        context.checking(new Expectations() {{
            oneOf(display).displayPriceNotFound("not found: not-found");
        }});

        itemScanned(barcode);
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Map<String, Price> createPricesByBarcodesWith(String barcode, Price price) {
        Map<String, Price> pricesByBarcodes = new HashMap<>();
        pricesByBarcodes.put(barcode, price);
        pricesByBarcodes.put("other-than-" + barcode, price);
        pricesByBarcodes.put("yet-other-than-" + barcode, price);
        return pricesByBarcodes;
    }

    private Map<String, Price> createPriceCatalogWithout(String barcode) {
        Map<String, Price> pricesByBarcodes = new HashMap<>();
        pricesByBarcodes.put("other-than-" + barcode, Price.euros(1));
        pricesByBarcodes.put("yet-other-than-" + barcode, Price.euros(2));
        return pricesByBarcodes;
    }

    private Map<String, Price> pricesByBarcode;
    private Display display = context.mock(Display.class);

    private void itemScanned(String barcode) {
        Price price = pricesByBarcode.get(barcode);
        if (price == null) {
            display.displayPriceNotFound(String.format("not found: %s", barcode));
        } else {
            display.displayTotal(String.format("total: %s", price));
        }
    }

    public interface Display {
        void displayTotal(String total);

        void displayPriceNotFound(String notFound);
    }

    private static class Price {
        private static final int CENTS_IN_EUR = 100;

        public static Price euros(int euros) {
            return Price.euros(euros, 0);
        }

        public static Price euros(int euros, int cents) {
            return new Price(euros, cents);
        }

        private final int valueInCents;

        private Price(int euros, int cents) {
            valueInCents = euros * CENTS_IN_EUR + cents;
        }

        @Override
        public String toString() {
            BigDecimal valueToFormat = new BigDecimal(BigInteger.valueOf(valueInCents), 2);
            return new DecimalFormat("#####.00 €").format(valueToFormat);
        }
    }
}
