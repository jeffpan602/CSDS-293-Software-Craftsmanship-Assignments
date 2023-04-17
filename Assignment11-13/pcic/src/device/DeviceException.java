package device;


import bus.Bus;

public class DeviceException extends Exception {

    //Error types for Messages
    public enum Error {
        NULL_POINTERS, INVALID_DEVICE, INVALID_PORT
    }
    //Error field for a Message exception
    private final Error e;

    public DeviceException(Error e) { this.e = e; }

    public static void verifyNonNull(Object... arr) {
        for (Object o : arr) {
            if (o == null) {
                throw new IllegalArgumentException(new DeviceException(Error.NULL_POINTERS));
            }
        }
    }

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

    public static void verifyPort(int portID, Device device) {
        if(!device.getPortMap().containsKey(portID))
            throw new IllegalArgumentException(new DeviceException(Error.INVALID_PORT));
    }
}
