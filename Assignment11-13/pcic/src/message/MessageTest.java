package message;

import org.junit.*;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testGetRecipientID() {
        Message message = new SSDMessage(10, 15, "101010", false);
        assertEquals(message.getRecipientID(), 10);
    }
    @Test
    public void testGetPortID() {
        Message message = new SSDMessage(10, 15, "101010", false);
        assertEquals(message.getPortID(), 15);
    }
    @Test
    public void testGetPayload() {
        Message message = new SSDMessage(10, 15, "101010", false);
        assertEquals("101010", message.getPayload());
    }
    @Test
    public void testIsBroadcast() {
        Message message = new SSDMessage(10, 15, "101010", false);
        assertFalse(message.isBroadcast());
    }
}
