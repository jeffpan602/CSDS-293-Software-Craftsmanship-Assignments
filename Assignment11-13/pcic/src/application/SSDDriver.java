package application;

import message.Message;

/**
 * Class representing the SSDriver application that can connect to Devices
 * @author Jeffrey Pan
 */
public class SSDDriver extends Application {
    /**
     * Constructor to create an instance of an SSDDriver
     * @param name String name of this SSDDriver
     */
    public SSDDriver(String name) {
        super(name);
    }

    /**
     * Method to process the message this SSDDriver receives
     * @param message Message instance received by this SSDDriver
     * @return String of the Message payload
     */
    public String process(Message message) {
        return message.getPayload();
    }
}
