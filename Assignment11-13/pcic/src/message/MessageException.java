package message;

public class MessageException extends Exception {
    //Error types for Messages
    public enum Error {
        NULL_POINTERS, INVALID_ID
    }
    //Error field for a Message exception
    private final Error error;

    MessageException(Error e) {
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

    public static void verifyIdentifiers() {
        
    }
}
