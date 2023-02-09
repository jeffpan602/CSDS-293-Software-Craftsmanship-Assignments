package polygon;

import org.junit.*;
import java.util.Iterator;
import static org.junit.Assert.*;

public class Assignment3Tester {

    @Test
    public void testIndexPairIncrement() {

        IndexPair coordinate = new IndexPair(1, 1);
        coordinate = coordinate.increment(Direction.TOP);
        assertTrue(coordinate.compareTo(new IndexPair(1,2))==0);

    }
    @Test
    public void testGridIterator() {

        IndexPair coordinate = new IndexPair(1, 1);
        Grid grid = Grid.from(Rectangle.of(1,4,1,5));
        Iterator it = grid.iterator();

    }
    public static void main(String[] args) {
        IndexPair coordinate = new IndexPair(1, 1);
        Grid grid = Grid.from(Rectangle.of(1,4,1,5));
        Iterator<IndexPair> it = grid.iterator();
//        IndexPair a = it.next();
//        System.out.println("("+a.xIndex()+", "+ a.yIndex()+")");
//        IndexPair b = it.next();
//        System.out.println("("+b.xIndex()+", "+ b.yIndex()+")");
        while(it.hasNext()) {
            IndexPair next = it.next();
            System.out.println("("+next.xIndex()+", "+ next.yIndex()+")");
        }
    }
}


