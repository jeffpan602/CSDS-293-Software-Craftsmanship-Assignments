package bus;

/**
 * Class to generate Bus ids uniquely
 * @author Jeffrey Pan
 */
public class BusIDGenerator {
    // field storing the current highest id
    private static int currentID = 0;

    /**
     * Method to generate to set a Bus id and increment currentID field
     * @return int id that is generated
     */
    public static int generateBusID() {
        int id = currentID;
        currentID++;
        return id;
    }

    /**
     * Getter to return the current highest id
     * @return int id
     */
    public static int getCurrentID() { return currentID; }
}
