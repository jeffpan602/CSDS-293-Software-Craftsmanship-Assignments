package polygon;

import java.util.Collection;
import java.util.EnumMap;

public class Rectangle<T> {
    private final EnumMap<Direction,  T> borders;

    public Rectangle(EnumMap<Direction, T> borders) {
        this.borders = borders;
    }

    /**
     * @param direction
     * @return the border corresponding to the given direction bound
     */
    T getBorder(Direction direction) {
      return borders.get(direction);
    }
    /**
     * @param directions
     * @return the borders corresponding to the given directions bounds
     */
    EnumMap getBorders(Collection<Direction> directions) {
        EnumMap<Direction, T> ans = new EnumMap<Direction, T>(Direction.class);
        for(Direction direction: directions) {
            ans.put(direction, borders.get(direction));
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
