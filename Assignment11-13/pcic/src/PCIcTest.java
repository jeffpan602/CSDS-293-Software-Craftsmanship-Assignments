import application.SSDDriver;
import bus.Bus;
import device.Device;
import device.SATADriver;
import message.Message;
import message.SSDMessage;
import org.junit.*;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class PCIcTest {

    @Test
    public void test() {
        Bus bus = new Bus(new LinkedList<Device>());
        SATADriver sata = new SATADriver();
        SATADriver sata2 = new SATADriver();
        sata.configureToBus(bus);
        sata2.configureToBus(bus);
        sata.configurePort(80, new SSDDriver("ssd"));
        Message message = new SSDMessage(bus.getID(), 80, "10101", false);
        boolean sent = sata.sendMessage(message, bus);
    }


}
