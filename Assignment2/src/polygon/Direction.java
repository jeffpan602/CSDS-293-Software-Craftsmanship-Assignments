package polygon;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
enum Direction {
    LEFT("LEFT"), RIGHT("RIGHT"), BOTTOM("BOTTOM"), TOP("TOP");

    // Direction property indicating if the direction is horizontal (LEFT or RIGHT)
    private boolean horizontal;
    // Direction property indicating if the direction is increases the coordinate value (TOP or RIGHT)
    private boolean increment;
    static final Set<Direction> HORIZONTAL_BOUNDS = new HashSet<>(Arrays.asList(Direction.LEFT, Direction.RIGHT));
    static final Set<Direction> VERTICAL_BOUNDS = new HashSet<>(Arrays.asList(Direction.BOTTOM, Direction.TOP));
    private final String direction;
    private Direction(String direction) {
        assert(direction.equals("LEFT") || direction.equals("RIGHT") ||
                direction.equals("BOTTOM") || direction.equals("TOP"));
        this.direction = direction;
        switch(direction) {
            case "LEFT":
                this.horizontal = true;
                this.increment = false;
                break;
            case "RIGHT":
                this.horizontal = true;
                this.increment = true;
                break;
            case "TOP":
                this.horizontal = false;
                this.increment = true;
                break;
            case "BOTTOM":
                this.horizontal = false;
                this.increment = false;
                break;
        }
    }

}


