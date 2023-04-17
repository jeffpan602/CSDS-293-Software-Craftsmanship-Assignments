import application.SSDDriver;
import bus.Bus;
import device.Device;
import device.SATADriver;
import message.Message;
import message.SSDMessage;
import org.junit.*;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class PCIcTest {

    @Test
    public void test() {
        Bus bus = new Bus(new LinkedList<Device>());
        SATADriver sata = new SATADriver();
        sata.configureToBus(bus);
        sata.configurePort(80, new SSDDriver("ssd"));
        Message message = new SSDMessage(bus.getID(), 80, "10101", false);
        boolean sent = sata.sendMessage(message, bus);
        assertTrue(sent);
        boolean processed = bus.processMessage(message);
        assertTrue(processed);
        boolean received = sata.receiveMessage(message);
        assertTrue(received);

        sata.configurePort(81, new SSDDriver("ssd2"));
        Message message2 = new SSDMessage(bus.getID(), 80, "10101", true);
        sata.sendMessage(message2, bus);
    }

    @Test
    public void testPortDNE() {
        Bus bus = new Bus(new LinkedList<Device>());
        SATADriver sata = new SATADriver();
        sata.configureToBus(bus);
        sata.configurePort(80, new SSDDriver("ssd"));
        Message message = new SSDMessage(bus.getID(), 81, "10101", false);
        boolean processed = bus.processMessage(message);
        assertFalse(processed);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDeviceOnBus() {
        Bus bus = new Bus(new LinkedList<Device>());
        SATADriver sata = new SATADriver();
        Message message = new SSDMessage(bus.getID(), 81, "10101", false);
        bus.processMessage(message);
    }
}
