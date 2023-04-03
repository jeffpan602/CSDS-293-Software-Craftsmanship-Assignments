package geology;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

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
        Landscape landscape1 = new Landscape(5);
        landscape1.modify(1,5, Modification.RAISE);
        int[] heights1 = {1, 2, 3, 2, 1};
        int heights1Index = 0;
        for(Point point: landscape1.getPoints()) {
            assertEquals(heights1[heights1Index], point.getY());
        }
    }
    public static void main(String[] args) {
        Landscape landscape1 = new Landscape(5);
        landscape1.modify(1,5, Modification.RAISE);
        for(Point point: landscape1.getPoints()) {
            System.out.println(point.getY());
        }
    }
}
