import org.junit.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class SortingTester {

    @Test
    public void testAlmostSortedListSort() {
        //Good Data Test (Legacy Test)
        List<Integer> list1 = new LinkedList<>(Arrays.asList(2, 1, 3, 5, 4, 7, 6));
        List<Integer> sortedList1 = Sorting.almostSortedListSort(list1, 1);

        int i = 1;
        for(Integer element: sortedList1) {
            assertEquals(i, (int) element);
            i++;
        }
        //Good Data Test (Legacy Test)
        List<Integer> list2 = new LinkedList<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        List<Integer> sortedList2 = Sorting.almostSortedListSort(list2, 9);

        int j = 1;
        for(Integer element: sortedList2) {
            assertEquals(j, (int) element);
            j++;
        }
    }
}
