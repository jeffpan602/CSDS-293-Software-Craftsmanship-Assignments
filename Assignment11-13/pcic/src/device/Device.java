package device;
import application.*;
import bus.Bus;
import bus.BusIDGenerator;
import message.Message;
import message.MessageException;

import java.util.HashMap;
import java.util.Map;

public abstract class Device {

    private final int id;
    private final Map<Integer, Application> portMap;
    private int busID;
    
    public Device(Map<Integer, Application> portMap, Bus bus) {
        DeviceException.verifyNonNull(portMap, bus);
        this.id = DeviceIDGenerator.generateDeviceID();
        this.portMap = portMap;
        this.busID = bus.getID();
        bus.addDevice(this);
    }

    public Device() {
        this.id = DeviceIDGenerator.generateDeviceID();
        this.portMap = new HashMap<Integer, Application>();
        this.busID = -1;
    }

    //getter methods for the fields of this Device
    public int getID() { return this.id; }
    public Map<Integer, Application> getPortMap() { return this.portMap; }
    public int getBusID() { return this.busID; }

    public void recieveMessage(Message message) {
        MessageException.verifyIdentifiers(message.getRecipientID(), message.getPortID(), this);
        if(message.isBroadcast()) {
            for(Integer portNum: getPortMap().keySet()) {
                getPortMap().get(portNum).process(message);
            }
        }
        else {
            getPortMap().get(message.getPortID()).process(message);
        }
    }

    public void sendMessage(Message message, Bus bus) {
        bus.processMessage(message);
    }

    public void configurePort(int portNum, Application application) {
        if(this.getPortMap().containsKey(portNum))
            throw new IllegalArgumentException(new DeviceException(DeviceException.Error.INVALID_PORT));
        getPortMap().put(portNum, application);
    }

    public void configureToBus(Bus bus) {
        this.busID = bus.getID();
        bus.addDevice(this);
    }
}
