package polygon;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PlaneMap<S> {

    //PlaneMap variable to hold the horizontal axis
    private final AxisMap<S> x;
    //PlaneMap variable to hold the vertical axis
    private final AxisMap<S> y;

    private PlaneMap(AxisMap<S> x, AxisMap<S> y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns a new PlaneMap based on a given list of horizontal and vertical coordinates
     * @param x Collection of x coordinates
     * @param y Collection of y coordinates
     * @return new PlaneMap based on AxisMap x and y coordinates
     */
    public static <S extends Comparable<S>> PlaneMap<S> of(Collection<S> x, Collection<S> y) {
        if(x == null || y == null) {
            throw new IllegalArgumentException("Inputs x and y must be non-null");
        }

        return new PlaneMap<>(AxisMap.from(x), AxisMap.from(y));
    }
    /**
     * Returns the index of the horizontal input value
     * @param value coordinate value of on the horizontal axis of this PlaneMap
     * @return index of the input value as an int
     * @param <T> generic type of the coordinate value
     */
    public <T extends S> Optional<Integer> xIndexOf(T value) {
        return getX().indexOf(value);
    }
    /**
     * Returns the index of the vertical input value
     * @param value coordinate value of on the vertical axis of this PlaneMap
     * @return index of the input value as an int
     * @param <T> generic type of the coordinate value
     */
    public <T extends S> Optional<Integer> yIndexOf(T value) {
        return getY().indexOf(value);
    }
    /**
     * Returns the size of this horizontal AxisMap
     * @return size of the horizontal AxisMap as an int
     */
    public int xSize() {
        return getX().size();
    }
    /**
     * Returns the size of this vertical AxisMap
     * @return size of the vertical AxisMap as an int
     */
    public int ySize() {
        return getY().size();
    }
    /**
     * Returns the flat index of the value along the given direction
     * @param value coordinate value on AxisMap
     * @param horizontal boolean if the AxisMap is horizontal or not
     * @return flat index as an int
     * @param <T> generic type of the coordinate value
     */
    <T extends S> Integer indexOf(T value, boolean horizontal) {

        return horizontal ? getX().flatIndexOf(value) : getY().flatIndexOf(value);
    }

    /**
     * Returns a new PlaneMap whose axes correspond to the border coordinates that appear explicitly in the rectangles
     * @param rectangles rectangles set of Rectangles
     * @return PlaneMap based on border coordinates
     * @param <S> generic type for the values on the PlaneMap
     */
    public static <S extends Comparable<S>> PlaneMap<S> from(Set<Rectangle<S>> rectangles) {
        Collection<S> xCoordinates = new HashSet<>();
        Collection<S> yCoordinates = new HashSet<>();
        for(Rectangle<S> rectangle: rectangles) {
            xCoordinates.addAll(rectangle.getBorders(Direction.HORIZONTAL_BOUNDS).values());
            yCoordinates.addAll(rectangle.getBorders(Direction.VERTICAL_BOUNDS).values());
        }
        return new PlaneMap<>(AxisMap.from(xCoordinates), AxisMap.from(yCoordinates));
    }
    /**
     * Getter method for the horizontal AxisMap
     * @return horizontal AxisMap of this
     */
    public AxisMap<S> getX() {
        return this.x;
    }
    /**
     * Getter method for the vertical AxisMap
     * @return vertical AxisMap of this
     */
    public AxisMap<S> getY() {
        return this.y;
    }
}
