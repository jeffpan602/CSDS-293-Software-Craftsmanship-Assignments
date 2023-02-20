package polygon;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Grid implements Iterable<IndexPair> {

    //Rectangle variable for Grid
    private final Rectangle<Integer> rectangle;

    private Grid(Rectangle<Integer> rectangle) {
        this.rectangle = rectangle;
    }
    /**
     * Returns a new Grid given an input Rectangle
     * @param rectangle rectangle to provide grid borders
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
        return new Iterator<IndexPair>() {
            final int topBorder = getRectangle().top();
            final int rightBorder = getRectangle().right();
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
                else{
                    coordinate = new IndexPair(coordinate.xIndex()+1, getRectangle().bottom());
                }

                if((next.xIndex() >= rightBorder) || (next.yIndex() >= topBorder)) {
                    throw new NoSuchElementException("IndexPair out of range");
                }

                return next;
            }
        };
    }

    /**
     * Getter method for Grid rectangle field
     * @return Rectangle<Integer>
     */
    public Rectangle<Integer> getRectangle() {
        return this.rectangle;
    }
}
