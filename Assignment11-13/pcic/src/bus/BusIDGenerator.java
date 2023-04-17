package bus;

public class BusIDGenerator {

    private static int currentID = 0;

    public static int generateBusID() {
        int id = currentID;
        currentID++;
        return id;
    }

    public static int getCurrentID() { return currentID; }
}
