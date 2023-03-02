import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.List;

public class Assignment6 {

    /**
     * Sorting algorithm to sort a given list that is almost sorted with a distortion factor of k
     * The value of k denotes how close the list is to be fully sorted where the ith smallest element is somewhere between index i-k and i+k
     * Pre-conditions: inputs are non-null and 0 < k <= 100
     * Post-conditions: output is a sorted list with input list values in non-decreasing order
     * @param list almost sorted list
     * @param k distortion factor
     * @return input list sorted in non-decreasing order
     * @param <E> generic type for values in the list
     */
    public static <E extends Comparable<? super E>>  List<E> almostSortedListSort(List<E> list, int k) {

        Assignment6.checkForPreconditions(list, k);

        PriorityQueue<E> pq = new PriorityQueue<>();

        for(int i = 0; i <= k; i++) {
            pq.add(list.get(i));
        }

        List<E> output = new LinkedList<>();

        for(int i = k+1; i < list.size(); i++) {
            pq.add(list.get(i));
            output.add(pq.remove());
        }

        while(!pq.isEmpty()) {
            output.add(pq.remove());
        }

        return output;
    }
    //helper method to check preconditions
    private static <E extends Comparable<? super E>> void checkForPreconditions(List<E> list, int k) {
        if(k < 0 || k > 100) {
            throw new IllegalArgumentException("Distortion factor k must be at most 100");
        }
        for(E element: list) {
            if(element == null) {
                throw new IllegalArgumentException("All elements in the list must be non-null");
            }
        }
    }
}
