import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    public void displayTotalWhenPriceFound() {
        String barcode = "found";
        pricesByBarcode = createPricesByBarcodesWith(barcode, new BigDecimal(10));
        context.checking(new Expectations() {{
            oneOf(display).displayTotal("total: 10 €");
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

    private Map<String, BigDecimal> createPricesByBarcodesWith(String barcode, BigDecimal price) {
        Map<String, BigDecimal> pricesByBarcodes = new HashMap<>();
        pricesByBarcodes.put(barcode, price);
        pricesByBarcodes.put("other-than-" + barcode, price);
        pricesByBarcodes.put("yet-other-than-" + barcode, price);
        return pricesByBarcodes;
    }

    private Map<String, BigDecimal> createPriceCatalogWithout(String barcode) {
        Map<String, BigDecimal> pricesByBarcodes = new HashMap<>();
        pricesByBarcodes.put("other-than-" + barcode, new BigDecimal(1));
        pricesByBarcodes.put("yet-other-than-" + barcode, new BigDecimal(2));
        return pricesByBarcodes;
    }

    private Map<String, BigDecimal> pricesByBarcode;
    private Display display = context.mock(Display.class);

    private void itemScanned(String barcode) {
        BigDecimal price = pricesByBarcode.get(barcode);
        if (price == null) {
            display.displayPriceNotFound(String.format("not found: %s", barcode));
        } else {
            display.displayTotal(String.format("total: %s €", price));
        }
    }

    public interface Display {
        void displayTotal(String total);

        void displayPriceNotFound(String notFound);
    }
}
