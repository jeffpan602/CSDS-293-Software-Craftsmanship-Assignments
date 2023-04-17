package bus;
import device.*;
import message.Message;

import java.util.List;

public class Bus {
    private final int id;
    private final List<Device> devices;

    public Bus(List<Device> devices) {
        this.id = BusIDGenerator.generateBusID();
        this.devices = devices;
    }

    public int getID() { return this.id; }
    public List<Device> getDevices() { return this.devices; }

    public boolean processMessage(Message message) {
        int deviceIndex = DeviceException.verifyDevice(message.getRecipientID(), this);
        try {
            DeviceException.verifyPort(message.getPortID(), this.getDevices().get(deviceIndex));
        }
        catch (IllegalArgumentException e) {
            return false;
        }

        this.getDevices().get(deviceIndex).recieveMessage(message);
        analyzeMessage(message);
        return true;
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
