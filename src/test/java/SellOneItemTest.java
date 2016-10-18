import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void displayTotalWhenPriceFound() {
        context.checking(new Expectations() {{
            oneOf(display).displayTotal("total: 10 €");
        }});

        String barcode = "12345";
        itemScanned(barcode);
    }

    private Display display = context.mock(Display.class);

    private void itemScanned(String barcode) {
        if (barcode.equals("12345")) {
            display.displayTotal("total: 10 €");
        }
    }

    public interface Display {
        void displayTotal(String total);
    }
}
