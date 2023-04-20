package bus;
import device.*;
import message.Message;

import java.util.List;

/**
 * Class to represent the PCIc bus that holds devices and sends messages
 * @author Jeffrey Pan
 */
public class Bus {
    // id field that uniquely identifies this PCIc bus
    private final int id;
    // devices field that holds the list of Devices attached to this PCIc bus
    private final List<Device> devices;

    /**
     * Constructor to create an instance of a Bus with a list of devices connected
     * @param devices List of devices to be connected to this Bus
     */
    public Bus(List<Device> devices) {
        this.id = BusIDGenerator.generateBusID();
        this.devices = devices;
    }

    /**
     * Getter method to return the id from this Bus
     * @return int id of this Bus
     */
    public int getID() { return this.id; }

    /**
     * Getter method to return the list of Devices from this Bus
     * @return List<Devices> devices of this Bus
     */
    public List<Device> getDevices() { return this.devices; }

    /**
     * Method to process the Message sent from a Device
     * If the message is valid, message is sent to the proper Device to the processed
     * @param message Message input to be processed
     * @return boolean indicating whether the message has been processed amd sent to the device
     */
    public boolean processMessage(Message message) {
        int deviceIndex = DeviceException.verifyDevice(message.getRecipientID(), this);
        try {
            DeviceException.verifyPort(message.getPortID(), this.getDevices().get(deviceIndex));
        }
        catch (IllegalArgumentException e) {
            return false;
        }
        this.getDevices().get(deviceIndex).receiveMessage(message);
        analyzeMessage(message);
        return true;
    }

    /**
     * Method to analyze what the Message input does
     * @param message Message to be analyzed
     */
    public void analyzeMessage(Message message) {

        System.out.println(message.getPayload());
    }

    /**
     * Method to add a Device to this PCIc bus
     * @param device Input device to be added to this Bus
     */
    public void addDevice(Device device) {
        this.getDevices().add(device);
    }

    /**
     * Method to remove the input Device from this PCIc bus
     * @param device Device to be removed
     * @return boolean indicating if the Device has been removed (false if no such input Device exists)
     */
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
