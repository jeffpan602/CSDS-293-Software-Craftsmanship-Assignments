package polygon;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class PlaneMap<S> {

    //PlaneMap variable to hold the horizontal axis
    private AxisMap<S> x;
    //PlaneMap variable to hold the vertical axis
    private AxisMap<S> y;

    private PlaneMap(AxisMap<S> x, AxisMap<S> y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns a new PlaneMap based on a given list of horizontal and vertical coordinates
     * @param x Collection of x coordinates
     * @param y Collection of y coordinates
     * @return new PlaneMap based on AxisMap x and y coordinates
     * @param <S>
     */
    public static <S extends Comparable<S>> PlaneMap of(Collection<S> x, Collection<S> y) {
        return new PlaneMap(AxisMap.from(x), AxisMap.from(y));
    }
    /**
     * Returns the index of the horizontal input value
     * @param value
     * @return index of the input value as an int
     * @param <T>
     */
    public <T extends Comparable<T>> Optional<Integer> xIndexOf(T value) {
        return getX().indexOf(value);
    }
    /**
     * Returns the index of the vertical input value
     * @param value
     * @return index of the input value as an int
     * @param <T>
     */
    public <T extends Comparable<T>> Optional<Integer> yIndexOf(T value) {
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
     * @param value
     * @param horizontal
     * @return flat index as an int
     * @param <T>
     */
    <T extends Comparable<T>> Integer indexOf(T value, boolean horizontal) {
        if(horizontal) {
            return getX().flatIndexOf(value);
        }
        else {
            return getY().flatIndexOf(value);
        }
    }
    /**
     * Returns a new PlaneMap whose axes correspond to the border coordinates that appear explicitly in the rectangles
     * @param rectangles
     * @return PlaneMap based on border coordinates
     */
    public static PlaneMap from(Set<Rectangle> rectangles) {

        for(Rectangle rectangle: rectangles) {

        }
        return null;
    }
    /**
     * Getter method for the horizontal AxisMap
     * @return horizontal AxisMap of this
     */
    public AxisMap getX() {
        return this.x;
    }
    /**
     * Getter method for the vertical AxisMap
     * @return vertical AxisMap of this
     */
    public AxisMap getY() {
        return this.y;
    }
}
