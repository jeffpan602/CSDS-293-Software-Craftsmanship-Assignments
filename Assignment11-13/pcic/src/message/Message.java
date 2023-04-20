package message;

/**
 * Class to represent a Message that can be sent through the PCIc bus
 */
public abstract class Message {

    //Message field to store and represent the unique identifier that denotes the intended recipient of the message
    private final int recipientID;
    //Message field to assign the message to a specific application on the receiving device
    private final int portID;
    //Message field to store and represent the binary string of the contents of this message.Message
    private final String payload;
    private final boolean isBroadcast;

    /**
     * Constructor to create an instance of this Message
     * @param recipientID int recipientID for the id of the bus the message is sent to
     * @param portID int portID for the port the message is sent to
     * @param payload String payload representing a binary string
     * @param isBroadcast boolean isBroadcast indicating if the Message is broadcast
     */
    public Message(int recipientID, int portID, String payload, boolean isBroadcast) {
        MessageException.verifyNonNull(recipientID, portID, payload, isBroadcast);
        this.recipientID = recipientID;
        this.portID = portID;
        this.payload = payload;
        this.isBroadcast = isBroadcast;
    }

    /**
     * Getter method for recipientID
     * @return int recipientID
     */
    public int getRecipientID() { return this.recipientID; }

    /**
     * Getter method for portID
     * @return int portID
     */
    public int getPortID() { return this.portID; }

    /**
     * Getter method for payload
     * @return String payload
     */
    public String getPayload() { return this.payload; }

    /**
     * Getter method for isBroadcast
     * @return boolean isBroadcast
     */
    public boolean isBroadcast() { return this.isBroadcast; }
}
