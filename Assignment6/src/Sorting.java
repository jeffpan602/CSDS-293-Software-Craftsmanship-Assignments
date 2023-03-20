import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.List;

public class Sorting {
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
        //error checking
        Objects.requireNonNull(list);
        Sorting.verifyPreconditions(list, k);

        //let pq be a new priority queue
        PriorityQueue<E> queue = new PriorityQueue<>();

        //insert first k elements into the queue
        queue.addAll(list.subList(0,k+1));

        //let sortedList be a new list
        List<E> output = new LinkedList<>();

        //insert remaining elements from list into the queue
        //remove head of priority queue and add to the output list
        for(int i = k+1; i < list.size(); i++) {
            queue.add(list.get(i));
            output.add(queue.remove());
        }

        //remove remaining elements of priority queue and add to output list
        while(!queue.isEmpty()) {
            output.add(queue.remove());
        }
        //return output list as sorted list
        return output;
    }
    //helper method to check preconditions
    private static <E extends Comparable<? super E>> void verifyPreconditions(List<E> list, int k) {
        if(Objects.isNull(list)) {
            throw new IllegalArgumentException("Input list must be non-null");
        }
        if(k < 0 || 100 < k)  {
            throw new IllegalArgumentException("Distortion factor k must be at most 100");
        }
        for(E element: list) {
            if(element == null) {
                throw new IllegalArgumentException("All elements in the list must be non-null");
            }
        }
    }
}
