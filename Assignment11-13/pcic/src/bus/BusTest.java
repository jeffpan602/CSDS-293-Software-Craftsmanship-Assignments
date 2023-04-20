package bus;
import device.SATADriver;
import org.junit.*;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Test class for Bus
 * @author Jeffrey Pan
 */
public class BusTest {
    /**
     * Test method to test removeDevice()
     */
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
