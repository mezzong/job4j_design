import org.junit.Test;

import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void hello() {
        String excepted = "hello";
        String result = Trigger.hello();
        assertEquals(excepted, result);
    }
}