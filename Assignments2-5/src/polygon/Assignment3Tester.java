package polygon;

import org.junit.*;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;

public class Assignment3Tester {

    @Test
    public void testAxisMap() {
        HashSet<Integer> coordinates = new HashSet<>();
        coordinates.add(1);
        coordinates.add(10);
        coordinates.add(3);
        coordinates.add(2);
        AxisMap<Integer> axisMap = AxisMap.from(coordinates);
        assertEquals(0, (int) axisMap.flatIndexOf(1));
        assertEquals(3, (int) axisMap.flatIndexOf(10));
        assertEquals(2, (int) axisMap.flatIndexOf(3));
        assertEquals(1, (int) axisMap.flatIndexOf(2));
        assertEquals(4, axisMap.size());
        assertEquals(axisMap.indexOf(2), Optional.of(1));
        assertEquals(axisMap.flatIndexOf(100), null);

    }
    @Test
    public void testPlaneMap() {
        //testing PlaneMap.of()
        HashSet<Integer> xCoordinates = new HashSet<>();
        HashSet<Integer> yCoordinates = new HashSet<>();
        xCoordinates.add(1);
        xCoordinates.add(2);
        xCoordinates.add(10);
        xCoordinates.add(3);
        yCoordinates.add(1);
        yCoordinates.add(2);
        yCoordinates.add(3);
        PlaneMap planeMap = PlaneMap.of(xCoordinates, yCoordinates);
        assertEquals(planeMap.xIndexOf(3), Optional.of(2));
        assertEquals(planeMap.yIndexOf(3), Optional.of(2));
        assertEquals(planeMap.xSize(), 4);
        assertEquals(planeMap.ySize(), 3);

        //testing PlaneMap.indexOf()
        assertTrue(planeMap.indexOf(3, true) == 2);
        assertTrue(planeMap.indexOf(99, false) == null);
        assertTrue(planeMap.indexOf(10, true) == 3);

        //testing PlaneMap.from()
        HashSet<Rectangle<Integer>> rectangles = new HashSet<>();
        rectangles.add(Rectangle.of(0, 2, 0, 2));
        rectangles.add(Rectangle.of(3,10,0, 2));
        PlaneMap planeMap2 = PlaneMap.from(rectangles);
        assertTrue(planeMap2.indexOf(0,true) == 0);
        assertTrue(planeMap2.indexOf(2,true) == 1);
        assertTrue(planeMap2.indexOf(3,true) == 2);
        assertTrue(planeMap2.indexOf(10,true) == 3);
        assertTrue(planeMap2.getY().size()==2);
    }
    @Test
    public void testIndexPairIncrement() {

        IndexPair coordinate = new IndexPair(1, 1);
        coordinate = coordinate.increment(Direction.TOP);
        assertEquals(0, coordinate.compareTo(new IndexPair(1, 2)));
    }

    public static void main(String[] args) {
        IndexPair coordinate = new IndexPair(1, 1);
        Grid grid = Grid.from(Rectangle.of(1,4,1,5));

        for (IndexPair next : grid) {
            System.out.println("(" + next.xIndex() + ", " + next.yIndex() + ")");
        }
    }
}


