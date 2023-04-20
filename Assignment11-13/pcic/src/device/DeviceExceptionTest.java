package device;

import org.junit.*;


/**
 * Test class for DeviceException
 */
public class DeviceExceptionTest {
    /**
     * Method to test verifyNonNull()
     */
    @Test(expected = IllegalArgumentException.class)
    public void testVerifyNonNull() {
        Device device = new SATADriver(null, null);
    }
}
