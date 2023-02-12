package polygon;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Optional;


public class Assignment4Tester {

    @Test
    public void testRectangleGroup() {
        Rectangle<Integer> r1 = Rectangle.of(0,2,0,1);
        Rectangle<Integer> r2 = Rectangle.of(2,3,1,2);
        Rectangle<Integer> r3 = Rectangle.of(1,2,2,3);
        HashSet<Rectangle<Integer>> rSet = new HashSet<>();
        rSet.add(r1);
        rSet.add(r2);
        rSet.add(r3);

        RectangleGroup rectangleGroup = RectangleGroup.from(rSet);

        PlaneMap planeMap = rectangleGroup.getPlaneMap();
        assertTrue(planeMap.xSize()==4 && planeMap.ySize()==4);
        assertEquals(planeMap.xIndexOf(2), Optional.of(2));
        assertEquals(planeMap.yIndexOf(3), Optional.of(3));

        NavigableMap<IndexPair, Long> matrixGrid = rectangleGroup.getMatrixGrid();
        assertTrue(matrixGrid.get(new IndexPair(0,0)) == 1);
        assertTrue(matrixGrid.get(new IndexPair(1,0)) == 1);
        assertTrue(matrixGrid.get(new IndexPair(2,1)) == 1);
        assertTrue(matrixGrid.get(new IndexPair(1,2)) == 1);
        assertTrue(matrixGrid.get(new IndexPair(2,2)) == 0);

        assertTrue(rectangleGroup.isOverlapping() == false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleOfExceptions() {
        Rectangle<Integer> r1 = Rectangle.of(0,2,0,1);
        Rectangle<Integer> r2 = Rectangle.of(2,3,1,2);
        HashSet<Rectangle<Integer>> rSet = new HashSet<>();
        rSet.add(r1);
        rSet.add(r2);
        rSet.add(null);
        RectangleGroup rectangleGroup = RectangleGroup.from(rSet);
    }

    public static void main(String[] args) {
        Rectangle<Integer> r1 = Rectangle.of(0,2,0,1);
        Rectangle<Integer> r2 = Rectangle.of(2,3,1,2);
        Rectangle<Integer> r3 = Rectangle.of(1,2,2,3);
        HashSet<Rectangle<Integer>> rSet = new HashSet<>();
        rSet.add(r1);
        rSet.add(r2);
        rSet.add(r3);

        RectangleGroup rectangleGroup = RectangleGroup.from(rSet);
        System.out.println(rectangleGroup.matrixGridToString());
    }
}
