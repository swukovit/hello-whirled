package sorting;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collections;

import sorting.SelectionSorter;

import static org.junit.Assert.*;

public class SelectionSorterTest {

    @org.junit.Test
    public void testSelectionSortArrayList() throws Exception {
        ArrayList<Integer> actual = new ArrayList<>();
        Collections.addAll(actual, 1, 2, 99, -3, 5, 88, 2, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        ArrayList<Integer> expected = new ArrayList<>();
        Collections.addAll(expected, Integer.MIN_VALUE, -3, 0, 1, 2, 2, 5, 88, 99, Integer.MAX_VALUE );

        SelectionSorter sorter = new SelectionSorter();

        sorter.SelectionSortArrayList(actual);

        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void testSelectionSortArray() throws Exception {
        int [] actual   = new int[] { 1, 2, 99, -3, 5, 88, 2, 0, Integer.MAX_VALUE, Integer.MIN_VALUE };
        int [] expected = new int[] { Integer.MIN_VALUE, -3, 0, 1, 2, 2, 5, 88, 99, Integer.MAX_VALUE };

        SelectionSorter sorter = new SelectionSorter();

        sorter.SelectionSortArray(actual);

        assertArrayEquals(expected, actual);
    }
}
