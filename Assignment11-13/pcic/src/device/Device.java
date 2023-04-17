package device;
import application.*;
import bus.Bus;
import message.Message;
import message.MessageException;

import java.util.Map;

public abstract class Device {

    private final int id;
    private final Map<Integer, Application> portMap;
    private final int busID;
    
    public Device(int id, Map<Integer, Application> portMap, int busID) {
        this.id = id;
        this.portMap = portMap;
        this.busID = busID;
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
}
