package polygon;

import java.util.NavigableMap;
import java.util.Set;

final class RectangleGroup<S> {
    private Set<Rectangle> rectangleSet;
    private PlaneMap<S> planeMap;
    private int[][] matrix;
    //private final NavigableMap<IndexPair, Long> matrixGrid;
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
    /**
     * getter for the matrix field
     * @return int[][]
     */
    public int[][] getMatrix() {
        return matrix;
    }
    //private RectangleGroup constructor that does not through an exception
    private RectangleGroup(Set<Rectangle> rectangleSet, PlaneMap<S> planeMap, int[][] matrix) {
        this.rectangleSet = rectangleSet;
        this.planeMap = planeMap;
        this.matrix = matrix;
    }
    /**
     * Method to create a RectangleGroup from an input set of Rectangles
     * @param rectangles
     * @return RectangleGroup
     */
    static RectangleGroup from(Set<Rectangle> rectangles) {

        PlaneMap planeMap = PlaneMap.from(rectangles);
        int[][] matrix = new int[planeMap.ySize()][planeMap.xSize()];

        for(Rectangle rectangle: rectangles) {

        }

        return new RectangleGroup(rectangles, planeMap, matrix);
    }
}
