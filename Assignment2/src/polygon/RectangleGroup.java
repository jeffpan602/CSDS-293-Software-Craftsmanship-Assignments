package polygon;

import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

final class RectangleGroup<S> {
    private Set<Rectangle> rectangleSet;
    private PlaneMap<S> planeMap;
    private final NavigableMap<IndexPair, Long> matrixGrid;
    //private final boolean isOverlapping;
    /**
     * getter for the rectangleSet field
     * @return Set<Rectangle>
     */
    public Set<Rectangle> getRectangleSet() {
        return rectangleSet;
    }

    /**
     * getter for the planeMap field
     * @return PlaneMap
     */
    public PlaneMap<S> getPlaneMap() {
        return planeMap;
    }
    //private RectangleGroup constructor that does not through an exception
    private RectangleGroup(Set<Rectangle> rectangleSet, PlaneMap<S> planeMap, NavigableMap<IndexPair, Long> matrixGrid) {
        this.rectangleSet = rectangleSet;
        this.planeMap = planeMap;
        this.matrixGrid = matrixGrid;
    }
    /**
     * Method to create a RectangleGroup from an input set of Rectangles
     * @param rectangles
     * @return RectangleGroup
     */
    static RectangleGroup from(Set<Rectangle> rectangles) {

        PlaneMap planeMap = PlaneMap.from(rectangles);
        NavigableMap<IndexPair, Long> matrixGrid = new TreeMap<>();

        for(Rectangle rectangle: rectangles) {
            
        }

        return new RectangleGroup(rectangles, planeMap, null);
    }
}
