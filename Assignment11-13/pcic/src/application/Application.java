package application;
import message.Message;

public abstract class Application {
    //Application field to store the name of this Application
    private final String name;

    public Application(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
    //Method to perform the function of this Application based on a message
    public abstract void process(Message message);
}
