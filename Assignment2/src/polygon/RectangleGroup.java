package polygon;

import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

final class RectangleGroup<S> {
    private final Set<Rectangle<S>> rectangleSet;
    private final PlaneMap<S> planeMap;
    private final NavigableMap<IndexPair, Long> matrixGrid;
    //private final boolean isOverlapping;
    /**
     * getter for the rectangleSet field
     * @return Set<Rectangle>
     */
    public Set<Rectangle<S>> getRectangleSet() {
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
    private RectangleGroup(Set<Rectangle<S>> rectangleSet, PlaneMap<S> planeMap, NavigableMap<IndexPair, Long> matrixGrid) {
        this.rectangleSet = rectangleSet;
        this.planeMap = planeMap;
        this.matrixGrid = matrixGrid;
    }
    /**
     * Method to create a RectangleGroup from an input set of Rectangles
     * @param rectangles set of rectangles to create a RectangleGroup
     * @return RectangleGroup
     */
    static <S extends Comparable<S>> RectangleGroup<S> from(Set<Rectangle<S>> rectangles) {

        PlaneMap<S> planeMap = PlaneMap.from(rectangles);
        Grid planeMapGrid = Grid.from(Rectangle.of(0, planeMap.xSize(), 0, planeMap.ySize()));

        NavigableMap<IndexPair, Long> matrixGrid = new TreeMap<>();

        for(Rectangle<S> rectangle: rectangles) {

        }

        return new RectangleGroup<>(rectangles, planeMap, null);
    }
    private NavigableMap<IndexPair, Long> initializeMatrixGrid(Grid grid) {
        return null;
    }
}
