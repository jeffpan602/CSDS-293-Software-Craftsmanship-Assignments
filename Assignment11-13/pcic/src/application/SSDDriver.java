package application;

import message.Message;

public class SSDDriver extends Application {

    public SSDDriver(String name) {
        super(name);
    }
    
    public String process(Message message) {
        return message.getPayload();
    }
}
