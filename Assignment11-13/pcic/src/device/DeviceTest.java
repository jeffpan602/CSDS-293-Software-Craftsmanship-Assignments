package device;

import application.Application;
import application.SSDDriver;
import bus.Bus;
import bus.BusIDGenerator;
import org.junit.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Class to test Device methods
 */
public class DeviceTest {
    /**
     * Method to test getID()
     */
    @Test
    public void testGetID() {
        int id = DeviceIDGenerator.getCurrentID();
        Device device = new SATADriver();
        assertEquals(device.getID(), id);
    }

    /**
     * Method to test getPortMap()
     */
    @Test
    public void testGetPortMap() {
        Device deviceWithEmptyPortMap = new SATADriver();
        assertTrue(deviceWithEmptyPortMap.getPortMap().isEmpty());

        Bus bus = new Bus(new LinkedList<>());
        Map<Integer, Application> portMap = new HashMap<Integer, Application>();
        Device device = new SATADriver(portMap, bus);
        assertEquals(device.getPortMap(), portMap);

        SSDDriver driver = new SSDDriver("driver");
        SSDDriver driver2 = new SSDDriver("driver2");
        portMap.put(80,driver);
        portMap.put(81, driver2);
        assertEquals(driver, device.getPortMap().get(80));
        assertEquals(driver2, device.getPortMap().get(81));
    }

    /**
     * Method to test getBusID()
     */
    @Test
    public void testGetBusID() {
        Bus bus = new Bus(new LinkedList<>());
        Map<Integer, Application> portMap = new HashMap<Integer, Application>();
        Device device = new SATADriver(portMap, bus);
        assertEquals(device.getBusID(), bus.getID());
    }

    /**
     * Method to test configurePort()
     */
    @Test
    public void testConfigurePort() {
        Device device = new SATADriver();
        SSDDriver driver = new SSDDriver("driver");
        device.configurePort(80, driver);
        assertTrue(device.getPortMap().containsKey(80));
        assertEquals(device.getPortMap().get(80), driver);
    }

    /**
     * Method to test configureToBus()
     */
    @Test
    public void testConfigureToBus() {
        int busID = BusIDGenerator.getCurrentID();
        Bus bus = new Bus(new LinkedList<>());
        Device device = new SATADriver();
        device.configureToBus(bus);
        assertEquals(bus.getID(), busID);
        assertTrue(bus.getDevices().contains(device));
    }

    /**
     * Method to test configurePort() that will throw expcetions
     */
    @Test (expected = IllegalArgumentException.class)
    public void configurePortException() {
        Device emptyDevice = new SATADriver();
        emptyDevice.configurePort(80, new SSDDriver("SSD"));
        emptyDevice.configurePort(80, new SSDDriver("SSD2"));
    }
}
