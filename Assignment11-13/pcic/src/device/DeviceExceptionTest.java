package device;

import bus.Bus;
import org.junit.*;

import java.util.LinkedList;

public class DeviceExceptionTest {

    @Test (expected = IllegalArgumentException.class)
    public void testVerifyNonNull() {
        Device device = new SATADriver(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testVerifyPort() {
        Bus bus = new Bus(new LinkedList<>());

    }
}
