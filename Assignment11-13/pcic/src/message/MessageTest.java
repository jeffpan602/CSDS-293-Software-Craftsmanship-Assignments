package message;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Class to test Message
 * @author Jeffrey Pan
 */
public class MessageTest {
    /**
     * Method to test getRecipientID()
     */
    @Test
    public void testGetRecipientID() {
        Message message = new SSDMessage(10, 15, "101010", false);
        assertEquals(message.getRecipientID(), 10);
    }

    /**
     * Method to test getPortID()
     */
    @Test
    public void testGetPortID() {
        Message message = new SSDMessage(10, 15, "101010", false);
        assertEquals(message.getPortID(), 15);
    }

    /**
     * Method to test getPayload()
     */
    @Test
    public void testGetPayload() {
        Message message = new SSDMessage(10, 15, "101010", false);
        assertEquals("101010", message.getPayload());
    }

    /**
     * Method to test isBroadcast()
     */
    @Test
    public void testIsBroadcast() {
        Message message = new SSDMessage(10, 15, "101010", false);
        assertFalse(message.isBroadcast());
    }
}
