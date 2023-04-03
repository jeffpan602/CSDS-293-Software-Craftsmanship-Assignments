package geology;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LandscapeTest {

    @Test
    public void testRange() {
        Landscape landscape = new Landscape(100);
        assertEquals(100, landscape.getRange());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRangeExceptions() {
        Landscape landscape = new Landscape(-10);
        assertEquals(-10, landscape.getRange());
    }

    @Test
    public void testGetPoints() {
        Landscape landscape = new Landscape(10);
        int i = 0;
        for(Point point: landscape.getPoints()) {
            assertEquals(i, point.getX());
            assertEquals(0, point.getY());
            i++;
        }
    }

    @Test
    public void testModifyRAISE() {
        Landscape landscape = new Landscape(5);
        landscape.modify(1,5, Modification.RAISE);
        ArrayList<Integer> heights = new ArrayList<>();
    }
}
