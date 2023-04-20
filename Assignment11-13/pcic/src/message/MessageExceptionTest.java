package message;

import org.junit.*;

/**
 * Class to test MessageException
 * @author Jeffrey Pan
 */
public class MessageExceptionTest {
    /**
     * Method to test verifyNonNull()
     */
    @Test (expected = IllegalArgumentException.class)
    public void testVerifyNonNull() {
        Message message = new SSDMessage(10, 15, null, false);
    }
}
