package polygon;
import org.junit.*;

import static org.junit.Assert.*;

public class Assignment2Tester {

    @Test
    public void testDirection() {
        assertTrue(Direction.LEFT.isHorizontal());
        assertTrue(Direction.RIGHT.isIncrement());
        for(Direction direction: Direction.HORIZONTAL_BOUNDS) {
            assertTrue(direction.isHorizontal());
        }
        for(Direction direction: Direction.VERTICAL_BOUNDS) {
            assertFalse(direction.isHorizontal());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleOfExceptions() {
        //Rectangle.of(10, 10, null, 10);
        //Rectangle.of(null, null, null, null);
        Rectangle.of(10, 5, 10, 10);
    }

    @Test
    public void testRectangle() {
        //testing of
        Rectangle r1 = Rectangle.of(5, 10, 1, 3);
        assertEquals(r1.left(), 5);
        assertEquals(r1.right(), 10);
        assertEquals(r1.bottom(), 1);
        assertEquals(r1.top(), 3);

        //testing copyOf
        Rectangle r2 = Rectangle.copyOf(r1);
        assertEquals(r2.left(), 5);
        assertEquals(r2.right(), 10);
        assertEquals(r2.bottom(), 1);
        assertEquals(r2.top(), 3);

        //testing getBorder
        assertEquals(r2.getBorder(Direction.RIGHT), 10);
        assertEquals(r2.getBorder(Direction.BOTTOM), 1);

        //testing getBorders
        assertEquals(r2.getBorders(Direction.VERTICAL_BOUNDS).get(Direction.TOP), 3);
        assertEquals(r2.getBorders(Direction.VERTICAL_BOUNDS).get(Direction.BOTTOM), 1);
    }


}
