package device;
import application.*;
import bus.Bus;
import message.Message;
import message.MessageException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent a Device that can be connected to a PCIc bus
 * @author Jeffrey Pan
 */
public abstract class Device {
    // id field to uniquely represent a Device
    private final int id;
    // portMap field to represent all ports on this Device and the Applications connected to each port
    private final Map<Integer, Application> portMap;
    // busID field to represent which bus this Device is connected to
    private int busID;

    /**
     * Constructor to create an instance of a Device
     * @param portMap portMap of all Applications connected to their ports
     * @param bus Bus that this Device connects to
     */
    public Device(Map<Integer, Application> portMap, Bus bus) {
        DeviceException.verifyNonNull(portMap, bus);
        this.id = DeviceIDGenerator.generateDeviceID();
        this.portMap = portMap;
        this.busID = bus.getID();
        bus.addDevice(this);
    }

    /**
     * Constructor to create an empty device with an empty portMap and not connected to a bus
     */
    public Device() {
        this.id = DeviceIDGenerator.generateDeviceID();
        this.portMap = new HashMap<Integer, Application>();
        this.busID = -1;
    }

    /**
     * Getter method to return the Device id
     * @return int id for this Device
     */
    public int getID() { return this.id; }

    /**
     * Getter method to return the portMap of this Device
     * @return Map<Integer, Application> of this Device
     */
    public Map<Integer, Application> getPortMap() { return this.portMap; }

    /**
     * Getter method to return the busID of this Device
     * @return int busID for this Device
     */
    public int getBusID() { return this.busID; }

    /**
     * Method to receive a Message from the Bus this Device is connected to and process it
     * @param message Input Message to be received and processed
     * @return boolean indicating if the Message has been processed properly
     */
    public boolean receiveMessage(Message message) {
        if(message.isBroadcast()) {
            for(Application app: getPortMap().values()) {
                app.process(message);
            }
        }
        else {
            getPortMap().get(message.getPortID()).process(message);
        }
        return true;
    }
    /**
     * Method to send a Message through to the Bus this Device is connected to, to be processed
     * @param message Message to be sent
     * @param bus Bus to process the message
     * @return boolean indicating if the message has been sent
     */
    public boolean sendMessage(Message message, Bus bus) {
        bus.processMessage(message);
        return true;
    }

    /**
     * Method to configure a port on this Device, adding an Application to an empty port
     * @param portNum int port number of the port to be configured
     * @param application Application to be added at the portNum
     */
    public void configurePort(int portNum, Application application) {
        if(this.getPortMap().containsKey(portNum))
            throw new IllegalArgumentException(new DeviceException(DeviceException.Error.INVALID_PORT));
        getPortMap().put(portNum, application);
    }

    /**
     * Method to configure this Device to a Bus
     * @param bus Bus to be connected to this Device
     */
    public void configureToBus(Bus bus) {
        this.busID = bus.getID();
        bus.addDevice(this);
    }
}
