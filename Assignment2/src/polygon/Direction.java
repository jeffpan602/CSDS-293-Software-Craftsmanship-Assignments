package polygon;

import java.util.EnumSet;
import java.util.Set;
enum Direction {
    LEFT(true, false),
    RIGHT(true, true),
    BOTTOM(false, false),
    TOP(false, true);

    // Direction property indicating if the direction is horizontal (LEFT or RIGHT)
    private boolean horizontal;
    // Direction property indicating if the direction is increases the coordinate value (TOP or RIGHT)
    private boolean increment;

    static final Set<Direction> HORIZONTAL_BOUNDS = EnumSet.of(Direction.LEFT, Direction.RIGHT);
    static final Set<Direction> VERTICAL_BOUNDS = EnumSet.of(Direction.BOTTOM, Direction.TOP);

    /**
     * Private constructor for Direction enum
     * @param horizontal boolean argument to denote if the direction is horizontal
     * @param increment boolean argument to denote if the direction increases the coordinate value
     */
    private Direction(boolean horizontal, boolean increment) {
        this.horizontal = horizontal;
        this.increment = increment;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
    public boolean isIncrement() { return increment; }

}


