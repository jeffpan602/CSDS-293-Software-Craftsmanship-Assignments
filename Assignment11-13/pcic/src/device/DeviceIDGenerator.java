package device;

public class DeviceIDGenerator {
    private static int currentID = 0;

    public static int generateDeviceID() {
        int id = currentID;
        currentID++;
        return id;
    }

    public static int getCurrentID() {
        return currentID;
    }
}
