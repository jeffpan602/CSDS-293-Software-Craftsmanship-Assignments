package bus;
import device.*;
import java.util.List;

public class Bus {
    private final int id;
    private final List<Device> devices;

    public Bus(int id, List<Device> devices) {
        this.id = id;
        this.devices = devices;
    }
}
