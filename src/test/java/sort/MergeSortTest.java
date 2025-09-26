package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    private final Sort<Integer> sorter = new MergeSort<>();

    @Test
    void testEmptyArray() {
        Integer[] arr = {};
        sorter.sort(arr);
        assertArrayEquals(new Integer[]{}, arr);
    }

    @Test
    void testSingleElement() {
        Integer[] arr = {42};
        sorter.sort(arr);
        assertArrayEquals(new Integer[]{42}, arr);
    }

    @Test
    void testSortedArray() {
        Integer[] arr = {1, 2, 3, 4, 5};
        sorter.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseArray() {
        Integer[] arr = {5, 4, 3, 2, 1};
        sorter.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testRandomArray() {
        Integer[] arr = {3, 1, 4, 1, 5, 9, 2};
        sorter.sort(arr);
        assertArrayEquals(new Integer[]{1, 1, 2, 3, 4, 5, 9}, arr);
    }
}