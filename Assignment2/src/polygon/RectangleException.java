package polygon;

import java.util.HashSet;
import java.util.Set;

public final class RectangleException extends Exception {
    public static final long serialVersionUID = 293L;

    public enum Error {
        NULL_POINTERS, INVALID_BOUNDS
    }
    private final Error error;
    private final Set<Integer> indexes;
    private final Object lesserBound;
    private final Object greaterBound;

    //RectangleException in which a generic error occurs
    RectangleException(Error e) {
        this.error = e;
        this.indexes = null;
        this.lesserBound = null;
        this.greaterBound = null;
    }

    //RectangleException in which an error with rectangle indices occurs
    RectangleException(Set<Integer> indexSet) {
        this.error = Error.NULL_POINTERS;
        this.indexes = indexSet;
        this.lesserBound = null;
        this.greaterBound = null;
    }

    //RectangleException in which an error with rectangle boundaries occurs
    RectangleException(Error e, Object lessBound, Object greatBound) {
        this.error = e;
        this.indexes = null;
        this.lesserBound = lessBound;
        this.greaterBound = greatBound;
    }

    /**
     * Method to verify the input rectangular boundaries are proper
     * Throws an IllegalArgumentException with a INVALID_BOUNDS RectangleException containing the bounds
     * @param lesserBound the lesser rectangle bound
     * @param greaterBound the greater rectangle bound
     * @param <S> generic type for the rectangle border
     */
    public static <S extends Comparable<S>> void verifyBounds(S lesserBound, S greaterBound) {
        if(lesserBound.compareTo(greaterBound) > 0) {
            throw new IllegalArgumentException(new RectangleException(Error.INVALID_BOUNDS, lesserBound, greaterBound));
        }
    }

    /**
     * Method to verify if any input Object is null
     * Throws an IllegalArgumentException with a NULL_POINTERS RectangleException
     * @param arr inputs that can potentially be null
     */
    public static void verifyNonNull(Object... arr) {
        Set<Integer> indexes = new HashSet<>();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == null) {
                indexes.add(i);
            }
        }
        if(!indexes.isEmpty()) {
            throw new IllegalArgumentException(new RectangleException(indexes));
        }
    }

}
