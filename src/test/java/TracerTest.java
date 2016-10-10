import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

public class TracerTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    public interface Collaborator {
        void doWork();
    }

    @Test
    public void mockitoWorks() {
        Collaborator testDouble = Mockito.mock(Collaborator.class);
        testDouble.doWork();
        Mockito.verify(testDouble).doWork();
    }

    @Test
    public void jmockWorks() {
        final Collaborator testDouble = context.mock(Collaborator.class);
        context.checking(new Expectations() {{
            oneOf(testDouble).doWork();
        }});
        testDouble.doWork();
    }
}
