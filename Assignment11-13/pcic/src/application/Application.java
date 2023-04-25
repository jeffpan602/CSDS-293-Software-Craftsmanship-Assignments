package application;
import message.Message;

/**
 * Class representing Applications that can be connected to devices
 * @author Jeffrey Pan
 */
public abstract class Application {
    //Application field to store the name of this Application
    private final String name;

    /**
     * Constructor to create an instance of an Application
     * @param name String name for this Application
     */
    public Application(String name) {
        this.name = name;
    }

    /**
     * Getter method to get the name field of this Application
     * @return String field for name field
     */
    public String getName() { return this.name; }
    //Method to perform the function of this Application based on a message
    public abstract String process(Message message);
}
