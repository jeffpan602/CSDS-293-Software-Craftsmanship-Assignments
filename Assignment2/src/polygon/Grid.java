package polygon;

import java.util.HashSet;
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
        return new Grid(Rectangle.copyOf(rectangle));
    }

    /**
     * Creates an Iterator to iterate from a Rectangle's bottom-left corner coordinate up and to the right, omitting the top and right borders
     * @return new Iterator<IndexPair>
     */
    @Override
    public Iterator<IndexPair> iterator() {
        Iterator<IndexPair> it = new Iterator<IndexPair>() {

            int topBorder = getRectangle().top();
            int rightBorder = getRectangle().right();
            IndexPair coordinate = new IndexPair(getRectangle().left(), getRectangle().bottom());
            @Override
            public boolean hasNext() {
                return (coordinate.xIndex() < rightBorder) && (coordinate.yIndex() < topBorder);
            }
            @Override
            public IndexPair next() {
                IndexPair next = coordinate;
                if(coordinate.yIndex()+1 != topBorder) {
                    coordinate  = coordinate.increment(Direction.TOP);
                }
                else {
                    coordinate = new IndexPair(coordinate.xIndex()+1, getRectangle().bottom());
                }
                return next;
            }
        };
        return it;
    }

    /**
     * Getter method for Grid rectangle field
     * @return Rectangle<Integer>
     */
    public Rectangle<Integer> getRectangle() {
        return this.rectangle;
    }
}
