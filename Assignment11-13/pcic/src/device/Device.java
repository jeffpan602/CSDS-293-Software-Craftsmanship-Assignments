package device;
import application.*;
import java.util.Map;

public class Device {

    private final int id;
    private final Map<Integer, Application> portMap;
    
    public Device(int id, Map<Integer, Application> portMap) {
        this.id = id;
        this.portMap = portMap;
    }
}
