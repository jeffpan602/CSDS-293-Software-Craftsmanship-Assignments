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

        RectangleGroup<Integer> rectangleGroup = RectangleGroup.from(rSet);

        assertTrue(rectangleGroup.getRectangleSet().containsAll(rSet));

        PlaneMap<Integer> planeMap = rectangleGroup.getPlaneMap();
        assertTrue(planeMap.xSize()==4 && planeMap.ySize()==4);
        assertEquals(planeMap.xIndexOf(2), Optional.of(2));
        assertEquals(planeMap.yIndexOf(3), Optional.of(3));

        NavigableMap<IndexPair, Long> matrixGrid = rectangleGroup.getMatrixGrid();
        assertEquals(1, (long) matrixGrid.get(new IndexPair(0, 0)));
        assertEquals(1, (long) matrixGrid.get(new IndexPair(1, 0)));
        assertEquals(1, (long) matrixGrid.get(new IndexPair(2, 1)));
        assertEquals(1, (long) matrixGrid.get(new IndexPair(1, 2)));
        assertEquals(0, (long) matrixGrid.get(new IndexPair(2, 2)));

        assertFalse(rectangleGroup.isOverlapping());

        Rectangle<Double> a = Rectangle.of(2.71,3.14,0.0,1.0);
        Rectangle<Double> b = Rectangle.of(0.1,3.14,0.0,1.0);
        HashSet<Rectangle<Double>> rSet2 = new HashSet<>();
        rSet2.add(a);
        rSet2.add(b);

        RectangleGroup<Double> rectangleGroup2 = RectangleGroup.from(rSet2);

        PlaneMap<Double> planeMap2 = rectangleGroup2.getPlaneMap();
        assertEquals(3, planeMap2.xSize());
        assertTrue(rectangleGroup2.isOverlapping());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleGroupExceptions() {
        Rectangle<Integer> r1 = Rectangle.of(0,2,0,1);
        Rectangle<Integer> r2 = Rectangle.of(2,3,1,2);
        HashSet<Rectangle<Integer>> rSet = new HashSet<>();
        rSet.add(r1);
        rSet.add(r2);
        rSet.add(null);
        RectangleGroup<Integer> rectangleGroup = RectangleGroup.from(rSet);
    }

    public static void main(String[] args) {
        Rectangle<Integer> r1 = Rectangle.of(0,2,0,1);
        Rectangle<Integer> r2 = Rectangle.of(2,3,1,2);
        Rectangle<Integer> r3 = Rectangle.of(1,2,2,3);
        HashSet<Rectangle<Integer>> rSet = new HashSet<>();
        rSet.add(r1);
        rSet.add(r2);
        rSet.add(r3);

        RectangleGroup<Integer> rectangleGroup = RectangleGroup.from(rSet);
        System.out.println(rectangleGroup.matrixGridToString());

        Rectangle<Double> a = Rectangle.of(2.71,3.14,0.0,1.0);
        Rectangle<Double> b = Rectangle.of(0.1,3.14,0.0,1.0);
        HashSet<Rectangle<Double>> rSet2 = new HashSet<>();
        rSet2.add(a);
        rSet2.add(b);

        RectangleGroup<Double> rectangleGroup2 = RectangleGroup.from(rSet2);
        System.out.println(rectangleGroup2.matrixGridToString());

        Rectangle<Integer> x = Rectangle.of(0,2,0,1);
        Rectangle<Integer> y = Rectangle.of(2,3,1,2);
        Rectangle<Integer> z = Rectangle.of(1,2,0,3);
        HashSet<Rectangle<Integer>> rSet3 = new HashSet<>();
        rSet3.add(x);
        rSet3.add(y);
        rSet3.add(z);

        RectangleGroup<Integer> rectangleGroup3 = RectangleGroup.from(rSet3);
        System.out.println(rectangleGroup3.matrixGridToString());

        System.out.println(rectangleGroup.getMatrixGrid().toString());
        System.out.println(rectangleGroup.getMatrixGrid().lastKey().toString());
    }
}
