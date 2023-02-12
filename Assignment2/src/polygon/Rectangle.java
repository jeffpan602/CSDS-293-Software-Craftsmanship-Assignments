package polygon;

import java.util.Collection;
import java.util.EnumMap;

public class Rectangle<T> {
    private final EnumMap<Direction, T> borders;

    private Rectangle(EnumMap<Direction, T> borders) {
        this.borders = borders;
    }

    /**
     * Returns a new Rectangle from given its four arguments
     * @param left left border for rectangle
     * @param right right border for rectangle
     * @param bottom bottom border for rectangle
     * @param top top border for rectangle
     * @return new Rectangle constructed with input parameters
     */
    public static <T extends Comparable<T>> Rectangle<T> of(T left, T right, T bottom, T top) {
        //verify that all arguments are not null
        RectangleException.verifyNonNull(left, right, bottom, top);
        //verify that the horizontal bounds are proper
        RectangleException.verifyBounds(left, right);
        //verify that the vertical bounds are proper
        RectangleException.verifyBounds(bottom, top);

        EnumMap<Direction, T> borders = new EnumMap<>(Direction.class);
        borders.put(Direction.LEFT, left);
        borders.put(Direction.RIGHT, right);
        borders.put(Direction.BOTTOM, bottom);
        borders.put(Direction.TOP, top);
        return new Rectangle<>(borders);
    }

    /**
     * Returns a new copy of a given input Rectangle
     * @param rectangle input rectangle that will be copied
     * @return Rectangle copy to the argument rectangle
     * @param <T> generic type for rectangle border
     */
    public static <T extends Comparable<T>> Rectangle<T> copyOf(Rectangle<T> rectangle) {
        //verify that the input Rectangle is not null
        RectangleException.verifyNonNull(rectangle);

        EnumMap<Direction, T> borders = new EnumMap<>(Direction.class);
        borders.put(Direction.LEFT, rectangle.left());
        borders.put(Direction.RIGHT, rectangle.right());
        borders.put(Direction.BOTTOM, rectangle.bottom());
        borders.put(Direction.TOP, rectangle.top());
        return new Rectangle<>(borders);
    }

    /**
     * @param direction the direction of the border
     * @return the border corresponding to the given direction bound
     */
    T getBorder(Direction direction) {
        assert borders.containsKey(direction): "Rectangle Borders must be defined";
        return borders.get(direction);
    }
    /**
     * @param directions the directions of the borders
     * @return the borders corresponding to the given directions bounds
     */
    EnumMap<Direction, T> getBorders(Collection<Direction> directions) {
        assert !borders.isEmpty(): "Rectangle Borders must be defined";
        EnumMap<Direction, T> ans = new EnumMap<>(Direction.class);
        for(Direction direction: directions) {
            if(!ans.containsKey(direction)) {
                ans.put(direction, borders.get(direction));
            }
        }
        return ans;
    }

    public T bottom() {
        return getBorder(Direction.BOTTOM);
    }
    public T top() {
        return getBorder(Direction.TOP);
    }
    public T left() {
        return getBorder(Direction.LEFT);
    }
    public T right() {
        return getBorder(Direction.RIGHT);
    }


}
