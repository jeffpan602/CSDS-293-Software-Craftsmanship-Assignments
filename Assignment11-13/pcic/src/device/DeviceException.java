package device;


import bus.Bus;

/**
 * Exception class to verify and check for Device exceptions that can arise
 * @author Jeffrey Pan
 */
public class DeviceException extends Exception {

    //Error types for Messages
    public enum Error {
        NULL_POINTERS, INVALID_DEVICE, INVALID_PORT
    }
    //Error field for a Message exception
    private final Error e;

    /**
     * Constructor to create an instance of DeviceException
     * @param e Error enum indicating the type of this DeviceException error
     */
    public DeviceException(Error e) { this.e = e; }

    /**
     * Method to verify if all inputs are non-null
     * @param arr Inputs for the method
     */
    public static void verifyNonNull(Object... arr) {
        for (Object o : arr) {
            if (o == null) {
                throw new IllegalArgumentException(new DeviceException(Error.NULL_POINTERS));
            }
        }
    }

    /**
     * Method to verify if the input Bus has the Device in question
     * @param deviceID id of the Device that could be connected to the input Bus
     * @param bus Input Bus
     * @return int deviceIndex of the Device on the List devices of the input Bus
     */
    public static int verifyDevice(int deviceID, Bus bus) {
        int deviceIndex = -1;
        for(int i = 0; i < bus.getDevices().size(); i++) {
            if (bus.getDevices().get(i).getID() == deviceID) {
                deviceIndex = i;
                break;
            }
        }
        if(deviceIndex == -1) {
            throw new IllegalArgumentException(new DeviceException(Error.INVALID_DEVICE));
        }
        else {
            return deviceIndex;
        }
    }

    /**
     * Method to verify if the input Device has the input port number empty or not
     * @param portID int portID that to be checked if empty or not
     * @param device Device device
     */
    public static void verifyPort(int portID, Device device) {
        if(!device.getPortMap().containsKey(portID))
            throw new IllegalArgumentException(new DeviceException(Error.INVALID_PORT));
    }
}
