package polygon;

import java.util.Iterator;

public final class Grid implements Iterable<IndexPair> {

    //Rectangle variable for Grid
    private final Rectangle<Integer> rectangle;

    private Grid(Rectangle<Integer> rectangle) {
        this.rectangle = rectangle;
    }
    /**
     * Returns a new Grid given an input Rectangle
     * @param rectangle
     * @return Grid based on input Rectangle
     */
    public static Grid from(Rectangle<Integer> rectangle) {
        return new Grid(Rectangle.of(rectangle.left(), rectangle.right(), rectangle.bottom(), rectangle.top()));
    }

    /**
     * Creates an Iterator to iterate from a Rectangle's bottom-left corner coordinate up and to the right, omitting the top and right borders
     * @return new Iterator<IndexPair>
     */
    @Override
    public Iterator<IndexPair> iterator() {
        Iterator<IndexPair> it = new Iterator<IndexPair>() {
            

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public IndexPair next() {
                return null;
            }
        };
        return it;
    }
}
