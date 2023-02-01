package polygon;

import java.util.Collection;
import java.util.Optional;

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
     *
     * @param value
     * @return
     * @param <T>
     */
    public <T extends Comparable<T>> Optional<Integer> xIndexOf(T value) {
        return null;
    }

    /**
     *
     * @param value
     * @return
     * @param <T>
     */
    public <T extends Comparable<T>> Optional<Integer> yIndexOf(T value) {
        return null;
    }
}
