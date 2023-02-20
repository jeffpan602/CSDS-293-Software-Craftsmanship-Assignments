package polygon;

import org.junit.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RectangleGroupTester {

    @Test
    public void testComponent() {
        Rectangle<Integer> r1 = Rectangle.of(0,2,0,1);
        Rectangle<Integer> r2 = Rectangle.of(2,3,1,2);
        Rectangle<Integer> r3 = Rectangle.of(1,2,2,3);
        HashSet<Rectangle<Integer>> rSet = new HashSet<>();
        rSet.add(r1);
        rSet.add(r2);
        rSet.add(r3);

        RectangleGroup<Integer> rectangleGroup = RectangleGroup.from(rSet);

        assertTrue(rectangleGroup.getMatrixGrid().get(new IndexPair(0,0)) >= 1);
        assertFalse(rectangleGroup.getMatrixGrid().containsKey(new IndexPair(0, -1)));
        assertFalse(rectangleGroup.getMatrixGrid().get(new IndexPair(2, 0)) >= 1);
        assertFalse(rectangleGroup.isOverlapping());
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

//        Rectangle<Integer> r = rSet.stream().findFirst().orElseThrow(() -> new NoSuchElementException("Rectangle set is empty"));
////        System.out.println(r.left() + " " + r.bottom());
////        Set<IndexPair> component = RectangleGroup.component(new IndexPair(r.left(), r.bottom()),new HashSet<>(), rectangleGroup.getMatrixGrid());
////        for(IndexPair x: component) {
////            System.out.println(x);
////        }
//        System.out.println(RectangleGroup.isConnected(r, rectangleGroup.getPlaneMap(), rectangleGroup.getMatrixGrid()));

    }
}
