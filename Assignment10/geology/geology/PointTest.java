package geology;

import org.junit.*;
import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testGetX() {
        Point point = new Point(1,11);
        assertEquals(1, point.getX());
    }
    @Test
    public void testGetY() {
        Point point = new Point(1, 11);
        assertEquals(11, point.getY());
    }
    @Test
    public void testIncreaseY() {
        Point point = new Point(0, 10);
        point.increaseY(6);
        assertEquals(16, point.getY());
    }
    @Test
    public void testDecreaseY() {
        Point point = new Point(10, 0);
        point.decreaseY(8);
        assertEquals(2, point.getY());
    }
}
