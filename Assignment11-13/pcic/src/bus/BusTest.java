package bus;
import device.SATADriver;
import org.junit.*;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.*;
public class BusTest {

    @Test
    public void testRemoveDevice() {
        Bus bus = new Bus(new LinkedList<>());
        SATADriver sata = new SATADriver();
        SATADriver sata2 = new SATADriver();
        sata.configureToBus(bus);
        sata2.configureToBus(bus);
        boolean removed = bus.removeDevice(sata);
        assertTrue(removed);


        SATADriver sata3 = new SATADriver();
        boolean removed3 = false;
        bus.removeDevice(sata3);
        assertFalse(removed3);
    }

}
