package application;

import org.junit.*;
import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void testGetName() {
        Application ssdDriver = new SSDDriver("ssdDriver1");
        assertEquals(ssdDriver.getName(), "ssdDriver1");
    }
}
