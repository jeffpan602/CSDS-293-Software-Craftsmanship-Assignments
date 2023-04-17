package message;

import device.Device;

public class MessageException extends Exception {
    //Error types for Messages
    public enum Error {
        NULL_POINTERS, INVALID_DEVICE_ID, INVALID_PORT_ID
    }
    //Error field for a Message exception
    private final Error error;

    public MessageException(Error e) {
        this.error = e;
    }

    /**
     * Method to verify if any input Object is null
     * @param arr inputs that can potentially be null
     */
    public static void verifyNonNull(Object... arr) {
        for (Object o : arr) {
            if (o == null) {
                throw new IllegalArgumentException(new MessageException(Error.NULL_POINTERS));
            }
        }
    }

    public static void verifyIdentifiers(int recipientID, int portID, Device device) {
        if(recipientID != device.getBusID())
            throw new IllegalArgumentException(new MessageException(Error.INVALID_DEVICE_ID));
        if(!device.getPortMap().containsKey(portID))
            throw new IllegalArgumentException(new MessageException(Error.INVALID_PORT_ID));
    }
}
