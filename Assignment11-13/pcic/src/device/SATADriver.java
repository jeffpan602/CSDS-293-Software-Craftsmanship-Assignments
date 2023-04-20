package device;

import application.Application;
import bus.Bus;

import java.util.Map;

/**
 * Class to represent a SATA Driver Device that can connect to a PCIc bus
 */
public class SATADriver extends Device {
    /**
     * Constructor to create an instance of SATADriver
     * @param portMap input portMap with Applications mapped to ports
     * @param bus input Bus to be connected to this SATADriver
     */
    public SATADriver(Map<Integer, Application> portMap, Bus bus) {
        super(portMap, bus);
    }

    /**
     * Constructor to create an empty SATADriver
     */
    public SATADriver() {}
}
