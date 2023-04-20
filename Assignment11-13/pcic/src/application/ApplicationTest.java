package application;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Testing class for Application
 * @author Jeffrey Pan
 */
public class ApplicationTest {

    /**
     * Test method for getName()
     */
    @Test
    public void testGetName() {
        Application ssdDriver = new SSDDriver("ssdDriver1");
        assertEquals(ssdDriver.getName(), "ssdDriver1");
    }
}
