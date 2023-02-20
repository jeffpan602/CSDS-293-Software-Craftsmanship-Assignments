package polygon;

import org.junit.*;
import java.util.HashSet;

import static org.junit.Assert.*;

public class RectangleGroupTester {

    @Test
    public void testIsConnected() {
        //Test case 1 with non-connected rectangles (only rectangle edges shared)
        Rectangle<String> r1 = Rectangle.of("0","2","0","1");
        Rectangle<String> r2 = Rectangle.of("2","3","1","2");
        Rectangle<String> r3 = Rectangle.of("1","2","2","3");
        HashSet<Rectangle<String>> rSet = new HashSet<>();
        rSet.add(r1);
        rSet.add(r2);
        rSet.add(r3);
        RectangleGroup<String> rectangleGroup = RectangleGroup.from(rSet);

        assertFalse(rectangleGroup.isConnected());

        //Test case 2 with connected rectangles (rectangles overlap)
        Rectangle<String> r4 = Rectangle.of("0", "1", "1", "3");
        Rectangle<String> r5 = Rectangle.of("0", "2", "1", "2");
        Rectangle<String> r6 = Rectangle.of("1", "2", "0", "2");
        HashSet<Rectangle<String>> rSet2 = new HashSet<>();
        rSet2.add(r4);
        rSet2.add(r5);
        rSet2.add(r6);
        RectangleGroup<String> rectangleGroup2 = RectangleGroup.from(rSet2);

        assertTrue(rectangleGroup2.isConnected());

        //Test case 3 with connected rectangles (rectangles border each other and don't overlap)
        Rectangle<String> r7 = Rectangle.of("0", "2", "0", "1");
        Rectangle<String> r8 = Rectangle.of("1", "3", "1", "2");
        Rectangle<String> r9 = Rectangle.of("0", "2", "2", "3");
        HashSet<Rectangle<String>> rSet3 = new HashSet<>();
        rSet3.add(r7);
        rSet3.add(r8);
        rSet3.add(r9);
        RectangleGroup<String> rectangleGroup3 = RectangleGroup.from(rSet3);

        assertTrue(rectangleGroup3.isConnected());
    }
}
