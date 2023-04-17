package bus;
import device.*;
import message.Message;

import java.util.List;

public class Bus {
    private final int id;
    private final List<Device> devices;

    public Bus(int id, List<Device> devices) {
        this.id = id;
        this.devices = devices;
    }

    public int getID() { return this.id; }
    public List<Device> getDevices() { return this.devices; }

    public void processMessage(Message message) {
        int deviceIndex = DeviceException.verifyDevice(message.getRecipientID(), this);
        DeviceException.verifyPort(message.getPortID(), this.getDevices().get(deviceIndex));

        this.getDevices().get(deviceIndex).recieveMessage(message);
    }
    public void analyzeMessage(Message message) {
        System.out.println(message.getPayload());
    }

    public void addDevice(Device device) {
        this.getDevices().add(device);
    }
    
    public boolean removeDevice(Device device) {
        for(int i = 0; i < this.getDevices().size(); i++) {
            if (this.getDevices().get(i).getID() == device.getID()) {
                this.getDevices().remove(i);
                return true;
            }
        }
        return false;
    }
}
