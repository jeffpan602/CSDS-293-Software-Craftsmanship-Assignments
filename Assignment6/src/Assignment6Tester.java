import org.junit.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class Assignment6Tester {

    @Test
    public void testAlmostSortedListSort() {
        List<Integer> list1 = new LinkedList<>(Arrays.asList(2, 1, 3, 5, 4, 7, 6));
        List<Integer> sortedList1 = Assignment6.almostSortedListSort(list1, 1);

        int i = 1;
        for(Integer element: sortedList1) {
            assertTrue(i == element);
            i++;
        }
    }
    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>(Arrays.asList(2, 1, 3, 5, 4, 7, 6));
        List<Integer> sortedList1 = Assignment6.almostSortedListSort(list1, 1);

        System.out.println(list1);
        System.out.println(sortedList1);
    }
}
