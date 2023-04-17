import application.*;
import bus.*;
import message.*;
import device.*;
import org.junit.*;

import java.util.LinkedList;

public class PCIcTest {

    @Test
    public void testWorkingSendingMessage() {
        Bus bus = new Bus(new LinkedList<Device>());
        SATADriver sata = new SATADriver();
        SATADriver sata2 = new SATADriver();
        sata.configureToBus(bus);
        sata2.configureToBus(bus);
        sata.configurePort(80, new SSDDriver("ssd"));
        Message message = new SSDMessage(bus.getID(), 80, "10101", false);
        sata.sendMessage(message, bus);

    }
}
