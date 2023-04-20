package device;

/**
 * Class to generate unique Device IDs
 */
public class DeviceIDGenerator {
    // field to store current ID of Device
    private static int currentID = 0;

    /**
     * Method to generate a unique id for a Device instance
     * @return int id generated
     */
    public static int generateDeviceID() {
        int id = currentID;
        currentID++;
        return id;
    }

    /**
     * Method to get the current ID
     * @return int currentID
     */
    public static int getCurrentID() {
        return currentID;
    }
}
