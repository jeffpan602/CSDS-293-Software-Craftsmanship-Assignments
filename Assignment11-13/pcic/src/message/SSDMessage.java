package message;

public class SSDMessage extends Message{

    public SSDMessage(int recipientID, int portID, String payload, boolean isBroadcast) {
        super(recipientID, portID, payload, isBroadcast);
    }
}
