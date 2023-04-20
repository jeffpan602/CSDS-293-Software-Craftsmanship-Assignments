import application.SSDDriver;
import bus.Bus;
import device.Device;
import device.SATADriver;
import message.Message;
import message.SSDMessage;
import org.junit.*;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Class to test messaging across the PCIc bus
 * @author Jeffrey Pan
 */
public class PCIcTest {
    /**
     * Method to test sending a message across Devices on a PCIc bus
     */
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

    /**
     * Method to test messaging across Devices when no port on the receiving Device exists
     */
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

    /**
     * Message to test if a Device exists on a Bus
     */
    @Test (expected = IllegalArgumentException.class)
    public void testDeviceOnBus() {
        Bus bus = new Bus(new LinkedList<Device>());
        SATADriver sata = new SATADriver();
        Message message = new SSDMessage(bus.getID(), 81, "10101", false);
        bus.processMessage(message);
    }

    /**
     * Stress test for the PCIc design
     * Creates a bus with three Devices connected to it
     * Randomly creates 1000 payloads to random portNumbers on the 3 devices randomly
     * Checks if the payload is consistent throughout after the Application processes the Message
     */
    @Test
    public void stressTest() {
        Bus bus = new Bus(new LinkedList<Device>());
        SATADriver sata1 = new SATADriver();
        SATADriver sata2 = new SATADriver();
        SATADriver sata3 = new SATADriver();
        sata1.configureToBus(bus);
        sata2.configureToBus(bus);
        sata3.configureToBus(bus);
        SATADriver[] devices = {sata1, sata2, sata3};
        for(Device device: devices) {
            configurePorts(device);
        }

        Random rand = new Random();

        for(int i = 0; i < 1000; i++) {
            int randomNum = rand.nextInt(3);
            int randomPortNum = (int) (Math.random() * 11) + 80;
            String binaryString = generateRandomBinaryString();
            Message message = new SSDMessage(bus.getID(),randomPortNum, binaryString, false);
            devices[randomNum].sendMessage(message, bus);
            assertEquals(binaryString, devices[randomNum].getPortMap().get(randomPortNum).process(message));
        }
    }

    /**
     * Helper method to configure the Ports of the Devices in the stress test
     * @param device Random Device in stress test
     */
    private void configurePorts(Device device) {
        for(int i = 80; i < 91; i++) {
            device.configurePort(i, new SSDDriver("ssd"+i));
        }
    }

    /**
     * Helper method to generate random binary strings for the payload
     * @return randomly generated binary String
     */
    private String generateRandomBinaryString() {
        int length = 8; // specify the length of the binary string
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomBit = rand.nextInt(2); // generate either 0 or 1
            sb.append(randomBit);
        }
        return sb.toString();
    }
}
