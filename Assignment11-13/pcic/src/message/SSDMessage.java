package message;

/**
 * Class to represent a SSD message
 * @author Jeffrey Pan
 */
public class SSDMessage extends Message{
    /**
     * Constructor to create an instance of SSDMessage
     * @param recipientID int recipientID
     * @param portID int portID
     * @param payload String payload
     * @param isBroadcast boolean isBroadcast
     */
    public SSDMessage(int recipientID, int portID, String payload, boolean isBroadcast) {
        super(recipientID, portID, payload, isBroadcast);
    }
}
