package message;

public abstract class Message {

    //message.Message field to store and represent the unique identifier that denotes the intended recipient of the message
    private final int recipientID;
    //message.Message field to store and assign the message to a specific application on the receiving device
    private final int portID;
    //message.Message field to store and represent the binary string of the contents of this message.Message
    private final String payload;
    private final boolean isBroadcast;

    public Message(int recipientID, int portID, String payload, boolean isBroadcast) {
        this.recipientID = recipientID;
        this.portID = portID;
        this.payload = payload;
        this.isBroadcast = isBroadcast;
    }

    //Getter methods to return the fields of this message.Message
    public int getRecipientID() { return this.recipientID; }
    public int getPortID() { return this.portID; }
    public String getPayload() { return this.payload; }
    public boolean isBroadcast() { return this.isBroadcast; }
}
