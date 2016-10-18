import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

public class SellOneItemTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void displayTotalWhenPriceFound() {
        String barcode = "12345";
        pricesByBarcode = Collections.singletonMap(barcode, new BigDecimal(10));
        context.checking(new Expectations() {{
            oneOf(display).displayTotal("total: 10 €");
        }});

        itemScanned(barcode);
    }

    private Map<String, BigDecimal> pricesByBarcode;
    private Display display = context.mock(Display.class);

    private void itemScanned(String barcode) {
        BigDecimal price = pricesByBarcode.get(barcode);
        display.displayTotal(String.format("total: %s €", price));
    }

    public interface Display {
        void displayTotal(String total);
    }
}
