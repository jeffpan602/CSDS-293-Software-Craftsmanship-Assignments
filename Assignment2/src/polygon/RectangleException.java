package polygon;

import java.util.Comparator;
import java.util.Set;

public final class RectangleException extends Exception {
     public static final long serialVersionUID = 293L;
     public enum Error {
        NULL_POINTERS, INVALID_BOUNDS;
    }

    private final Error error;
    private final Set<Integer> indexes;
    private final Object lesserBound;
    private final Object greaterBound;

    RectangleException(Error e) {
        error = e;
        indexes = null;
        lesserBound = null;
        greaterBound = null;
    }

    RectangleException(Set<Integer> indexSet) {
        error = null;
        indexes = indexSet;
        lesserBound = null;
        greaterBound = null;
    }

    RectangleException(Error e, Object lessBound, Object greatBound) {
        error = e;
        indexes = null;
        lesserBound = lessBound;
        greaterBound = greatBound;
    }

    public static void verifyBounds(Object lesserBound, Object greaterBound) throws IllegalArgumentException{
        Comparator cmp = Comparator.naturalOrder();
        if(cmp.compare(lesserBound,greaterBound) > 0) {
            throw new IllegalArgumentException(new RectangleException(Error.INVALID_BOUNDS));
        }
    }

    public static void verifyNonNull(Object... arr) {
        for(Object o: arr) {
            if(o == null) {
                throw new IllegalArgumentException(new RectangleException(Error.NULL_POINTERS));
            }
        }
    }
}
