import org.junit.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import org.junit.Before;

public class SortingTest {

    //almostSortedListSort tests in accordance to the Test Design Document
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

        //Test Condition (1)
        List<Integer> list3 = new LinkedList<>(Arrays.asList(3, 10, 7, 22, 1));
        List<Integer> sortedList3 = Sorting.almostSortedListSort(list3, 4);
        list3 = list3.stream().sorted().collect(Collectors.toList());
        assertEquals(list3, sortedList3);

        //Test Condition (2)
        List<Integer> list4 = new LinkedList<>(Arrays.asList(3, 1));
        List<Integer> sortedList4 = Sorting.almostSortedListSort(list4, 1);
        list4 = list4.stream().sorted().collect(Collectors.toList());
        assertEquals(list4, sortedList4);

        //Test Condition (3)
        List<Integer> list5 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> sortedList5 = Sorting.almostSortedListSort(list5, 0);
        list5 = list5.stream().sorted().collect(Collectors.toList());
        assertEquals(list5, sortedList5);

        //Test Condition (4)
        List<Integer> emptyList = new LinkedList<>(Arrays.asList());
        List<Integer> sortedEmptyList = Sorting.almostSortedListSort(emptyList, 0);
        emptyList = emptyList.stream().sorted().collect(Collectors.toList());
        assertEquals(emptyList, sortedEmptyList);
    }
    //testing for exception through method (Bad Data Tests)
    @Test(expected = IllegalArgumentException.class)
    public void testAlmostSortedListSortListExceptions() {
        List<Integer> list = new LinkedList<>(Arrays.asList(10, 9, null));
        list = Sorting.almostSortedListSort(list, 1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAlmostSortedListSortDistortionFactorExceptions() {
        List<Integer> list = new LinkedList<>(Arrays.asList(10, 9));
        list = Sorting.almostSortedListSort(list, -1);

        list = Sorting.almostSortedListSort(list, 101);
    }
    //verifyListNonNull tests throw exceptions as expected when preconditions fail
    @Test(expected = IllegalArgumentException.class)
    public void testVerifyListNonNull() {
        List<Integer> list = new LinkedList<>(Arrays.asList(10, 9, null));
        Sorting.VerifyTesting.verifyListNonNull(null);
        Sorting.VerifyTesting.verifyListNonNull(list);
    }
    //verifyDistortionFactor tests throw exceptions as expected when preconditions fail
    @Test(expected = IllegalArgumentException.class)
    public void testVerifyDistortionFactor() {
        Sorting.VerifyTesting.verifyDistortionFactor(-1);
        Sorting.VerifyTesting.verifyDistortionFactor(101);
    }
}
