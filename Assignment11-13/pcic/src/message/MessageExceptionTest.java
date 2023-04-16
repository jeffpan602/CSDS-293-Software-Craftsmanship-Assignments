package message;

import org.junit.*;
import static org.junit.Assert.*;

public class MessageExceptionTest {

    @Test (expected = IllegalArgumentException.class)
    public void testVerifyNonNull() {
        Message message = new SSDMessage(10, 15, null, false);
    }
}
