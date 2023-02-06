package polygon;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AxisMap<S> {

    private Map<S, Integer> index;

    private AxisMap(Collection<S> coordinates) {
        this.index = index(coordinates);
    }

    /**
     * Returns the indices of input coordinates sorted by associated value
     * @param coordinates
     * @return Map of indices and the associated values sorted
     *
     * rename method to something else
     */
    private Map<S, Integer> index(Collection<S> coordinates) {
        assert coordinates != null: "Coordinates must have a value";

        Map<S, Integer> indexes = new HashMap<>();

        coordinates = coordinates.stream().sorted().collect(Collectors.toList());
        int i = 0;
        //check for duplicate axis values and don't include
        for(S coordinate: coordinates) {
            indexes.put(coordinate, i);
        }
        return indexes;
    }

    /**
     * @param coordinates
     * @return a  new  AxisMap  starting  from  the  given  coordinates
     * @param <S>
     */
     static <S extends Comparable<S>> AxisMap from(Collection<S> coordinates) {
         assert coordinates != null: "Coordinates must have a value";

         return new AxisMap(coordinates);
    }

    /**
     * @param value
     * @return the index of the given value, or null if the value does not have an index
     */
    Integer flatIndexOf(S value) {
        return this.index.get(value);
    }

    /**
     * Returns the number of points in the index
     * @return size of the index of the AxisMap as an int
     */
    public int size() {
        return this.index.size();
    }

    /**
     * Returns the index of the given value
     * @param value
     * @return index of the input value as an int or null if value is null or value has no index
     */
    public Optional<Integer> indexOf(S value) {

        return Optional.ofNullable(flatIndexOf(value));
    }
}
