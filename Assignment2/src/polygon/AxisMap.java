package polygon;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AxisMap<S> {
    private final Map<S, Integer> index;
    private AxisMap(Collection<S> coordinates) {
        this.index = sortIndexes(coordinates);
    }

    /**
     * Returns the indices of input coordinates sorted by associated value
     * @param coordinates coordinates as IndexPair
     * @return Map of indices and the associated values sorted
     */
    private Map<S, Integer> sortIndexes(Collection<S> coordinates) {
        assert coordinates != null: "Coordinates must have a value";

        Map<S, Integer> indexes = new HashMap<>();

        coordinates = coordinates.stream().sorted().collect(Collectors.toList());
        int i = 0;
        for(S coordinate: coordinates) {
            if(!indexes.containsKey(coordinate)) {
                indexes.put(coordinate, i);
                i++;
            }
        }
        return indexes;
    }

    /**
     * @param coordinates coordinates as IndexPair
     * @return a  new  AxisMap  starting  from  the  given  coordinates
     * @param <S> generic type for AxisMap value
     */
     static <S extends Comparable<S>> AxisMap<S> from(Collection<S> coordinates) {
         assert coordinates != null: "Coordinates must have a value";

         return new AxisMap<>(coordinates);
    }

    /**
     * @param value value on the input AxisMap
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
     * @param value value on the input AxisMap
     * @return index of the input value as an int or null if value is null or value has no index
     */
    public Optional<Integer> indexOf(S value) { return Optional.ofNullable(flatIndexOf(value)); }
}
