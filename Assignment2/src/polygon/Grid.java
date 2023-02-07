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

        //Making a PlaneMap based on this Grid
        HashSet<Rectangle> rectangleSet = new HashSet<>();
        rectangleSet.add(this.getRectangle());
        PlaneMap rectangleGrid = PlaneMap.from(rectangleSet);

        Iterator<IndexPair> it = new Iterator<IndexPair>() {
            //use rectangle bottom, left. etc
            IndexPair coordinate = new IndexPair((rectangleGrid.getX().flatIndexOf(0)), rectangleGrid.getY().flatIndexOf(0));
            int topBorder = rectangleGrid.getX().size();
            int rightBorder = rectangleGrid.getY().size();
            @Override
            public boolean hasNext() {
                return (coordinate.xIndex() < rightBorder) && (coordinate.yIndex() < topBorder);
            }

            @Override
            public IndexPair next() {
                if(coordinate.yIndex()+1 != topBorder) {
                    coordinate.increment(Direction.TOP);
                }
                else {
                    coordinate = new IndexPair(coordinate.xIndex()+1, rectangleGrid.getY().flatIndexOf(0));
                }
                return coordinate;
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
