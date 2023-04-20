package message;

import device.Device;

public class MessageException extends Exception {
    //Error types for Messages
    public enum Error {
        NULL_POINTERS, INVALID_DEVICE_ID, INVALID_PORT_ID
    }
    //Error field for a Message exception
    private final Error error;

    /**
     * Constructor to create an instance of MessageException
     * @param e Error enum indicating the type of this MessageException error
     */
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

}
