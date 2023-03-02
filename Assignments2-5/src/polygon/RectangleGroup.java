package polygon;

import java.util.*;
import java.util.stream.Collectors;

final class RectangleGroup<S> {
    private final Set<Rectangle<S>> rectangleSet;
    private final PlaneMap<S> planeMap;
    private final NavigableMap<IndexPair, Long> matrixGrid;
    private final boolean isOverlapping;
    private final boolean isConnected;
    /**
     * getter for the rectangleSet field
     * @return Set<Rectangle>
     */
    public Set<Rectangle<S>> getRectangleSet() {
        return Collections.unmodifiableSet(rectangleSet);
    }
    /**
     * getter for the planeMap field
     * @return PlaneMap
     */
    public PlaneMap<S> getPlaneMap() {
        return planeMap;
    }
    //private RectangleGroup constructor that does not throw an exception
    private RectangleGroup(Set<Rectangle<S>> rectangleSet, PlaneMap<S> planeMap, NavigableMap<IndexPair, Long> matrixGrid, boolean isOverlapping, boolean isConnected) {
        this.rectangleSet = rectangleSet;
        this.planeMap = planeMap;
        this.matrixGrid = matrixGrid;
        this.isOverlapping = isOverlapping;
        this.isConnected = isConnected;
    }
    /**
     * Method to create a RectangleGroup from an input set of Rectangles
     * @param rectangles set of rectangles to create a RectangleGroup
     * @return RectangleGroup
     */
    static <S extends Comparable<S>> RectangleGroup<S> from(Set<Rectangle<S>> rectangles) {
        //verify rectangles in set are non-null
        verifyNonNull(rectangles);

        //make a PlaneMap based on the set of rectangles
        PlaneMap<S> planeMap = PlaneMap.from(rectangles);

        //make a Grid based on the PlaneMap indexes of the horizontal and vertical AxisMaps
        Grid grid = Grid.from(Rectangle.of(0, planeMap.xSize()-1, 0, planeMap.ySize()-1));

        //make the NavigableMap with <IndexPair, 0L>, where IndexPairs are from iterating through the Grid
        NavigableMap<IndexPair, Long> matrixGrid = RectangleGroup.initializeMatrixGrid(grid);

        for(Rectangle<S> rectangle: rectangles) {
            //make Grid based off a copy of rectangle
            Rectangle<S> rectangleCopy = Rectangle.copyOf(rectangle);

//            for(Direction direction: Direction.values()) {
//
//            }
            //make enummap with direction and values and put into the constructor
            int left = planeMap.indexOf(rectangleCopy.getBorder(Direction.LEFT),Direction.LEFT.isHorizontal());
            int right = planeMap.indexOf(rectangleCopy.getBorder(Direction.RIGHT), Direction.RIGHT.isHorizontal());
            int bottom = planeMap.indexOf(rectangleCopy.getBorder(Direction.BOTTOM),Direction.BOTTOM.isHorizontal());
            int top = planeMap.indexOf(rectangleCopy.getBorder(Direction.TOP), Direction.TOP.isHorizontal());

            Grid rectangleGrid = Grid.from(Rectangle.of(left, right, bottom ,top));
            RectangleGroup.incrementValues(matrixGrid, rectangleGrid);
        }
        boolean isOverlapping = RectangleGroup.isOverlapping(matrixGrid);


        boolean isConnected = RectangleGroup.isConnected(rectangles.stream().findFirst().orElseThrow(() -> new NoSuchElementException("Rectangle set is empty")), planeMap, matrixGrid);

        return new RectangleGroup<>(rectangles, planeMap, matrixGrid, isOverlapping, isConnected);
    }
    /**
     * Getter method for isOverlapping field
     * @return boolean indicating if rectangles overlap in this Rectangle Group
     */
    public boolean isOverlapping() {
        return this.isOverlapping;
    }
    //helper method to initialize the NavigableMap with the IndexPairs of the input Grid's iterator
    private static NavigableMap<IndexPair, Long> initializeMatrixGrid(Grid grid) {
        NavigableMap<IndexPair, Long> matrixGrid = new TreeMap<>();
        for(IndexPair next: grid) {
            matrixGrid.put(next, 0L);
        }
        return matrixGrid;
    }
    //helper method to check if rectangles are overlapping in this RectangleGroup
    private static boolean isOverlapping(NavigableMap<IndexPair, Long> matrixGrid ) {
        for(IndexPair indexPair: matrixGrid.keySet()) {
            if(matrixGrid.get(indexPair) > 1) {
                return true;
            }
        }
        return false;
    }
    //helper method to increment IndexPair keys in matrixGrid
    private static void incrementValues(NavigableMap<IndexPair, Long> matrixGrid, Grid rectangleGrid) {
        for(IndexPair next: rectangleGrid) {
            matrixGrid.replace(next, matrixGrid.get(next)+1);
        }
    }
    /**
     * Getter method for matrixGrid field
     * @return NavigableMap<IndexPair, Long> that represents the matrix grid of this RectangleGroup
     */
    public NavigableMap<IndexPair, Long> getMatrixGrid() {
        return Collections.unmodifiableNavigableMap(this.matrixGrid);
    }
    //helper method to verify Rectangle.from inputs are non-null
    //use RectangleException
    private static <S> void verifyNonNull(Set<Rectangle<S>> set) {
        for(Rectangle<S> rectangle: set) {
            if(rectangle == null) {
                throw new IllegalArgumentException(new RectangleException(RectangleException.Error.NULL_POINTERS));
            }
        }
    }
    /**
     * method to return a String representation of the matrixGrid
     * @return String visualization of the matrixGrid
     */
    public String matrixGridToString() {
        long[][] matrix = new long[getPlaneMap().ySize()-1][getPlaneMap().xSize()-1];
        for(IndexPair next: getMatrixGrid().keySet()) {
            matrix[next.yIndex()][next.xIndex()] = getMatrixGrid().get(next);
        }
        StringBuilder builder = new StringBuilder();
        for (long[] row : matrix) {
            builder.append(Arrays.toString(row));
            builder.append("\n");
        }
        return builder.toString();
    }
    /**
     * Algorithm to find the rectangles reachable from a given start IndexPair point
     * @return a set of IndexPairs from RectangleGroup that are connected
     */
     private static Set<IndexPair> component(IndexPair start, Set<IndexPair> current, NavigableMap<IndexPair, Long> grid) {
        //add asserts 
        Set<IndexPair> connectedPairs = current;

        connectedPairs.add(start);
        for(Direction direction: Direction.values()) {
            IndexPair next = start.increment(direction);
            if(grid.containsKey(next)  && grid.get(next) >= 1 && !RectangleGroup.currentHasNext(connectedPairs, next)) {
                connectedPairs = component(next, connectedPairs, grid);
            }
        }
        return connectedPairs;
    }
    //helper method to implement COMPONENT to determine if all rectangles are connected
     private static <S> boolean isConnected(Rectangle<S> rectangle, PlaneMap<S> planeMap, NavigableMap<IndexPair, Long> grid) {

        int left = planeMap.indexOf(rectangle.getBorder(Direction.LEFT),Direction.LEFT.isHorizontal());
        int bottom = planeMap.indexOf(rectangle.getBorder(Direction.BOTTOM),Direction.BOTTOM.isHorizontal());
        IndexPair start = new IndexPair(left, bottom);

        Set<IndexPair> component = component(start,new HashSet<>(), grid);

        return component.size() == grid.entrySet().stream().filter(i -> i.getValue() >= 1).map(Map.Entry::getKey).collect(Collectors.toSet()).size();
    }
    /**
     * Getter method to return isConnected field
     * @return boolean indicating if this RectangleGroup has its rectangles connected
     */
    public boolean isConnected() {
        return isConnected;
    }
    //helper method to check if input IndexPair set contains input IndexPair value
    private static boolean currentHasNext(Set<IndexPair> set, IndexPair value) {
        for(IndexPair pair: set) {
            if(pair.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
