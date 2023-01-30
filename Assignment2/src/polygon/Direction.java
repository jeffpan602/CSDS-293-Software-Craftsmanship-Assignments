package polygon;
import java.util.Set;
enum Direction {
    LEFT("LEFT"), RIGHT("RIGHT"), BOTTOM("BOTTOM"), TOP("TOP");

    // Direction property indicating if the direction is horizontal (LEFT or RIGHT)
    private static boolean horizontal;
    // Direction property indicating if the direction is increases the coordinate value (TOP or RIGHT)
    private static boolean increment;
    private final String direction;
    private Direction(String direction) {
        this.direction = direction;
    }

}


