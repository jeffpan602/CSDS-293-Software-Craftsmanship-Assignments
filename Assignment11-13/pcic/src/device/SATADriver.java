package device;

import application.Application;
import bus.Bus;

import java.util.Map;

public class SATADriver extends Device {

    public SATADriver(Map<Integer, Application> portMap, Bus bus) {
        super(portMap, bus);
    }

    public SATADriver() {}
}
