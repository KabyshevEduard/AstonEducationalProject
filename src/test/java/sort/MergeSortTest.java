package sort;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testSortIntegers() {
        Integer[] arrayA = {9, 4, 6, 2, 7};
        Sort<Integer> sorter = new MergeSort<>(Comparator.naturalOrder());
        sorter.sort(arrayA);
        assertArrayEquals(new Integer[]{2, 4, 6, 7, 9}, arrayA);
    }

    @Test
    void testSortStrings() {
        String[] arrayA = {"dog", "cat", "elephant", "bee"};
        Sort<String> sorter = new MergeSort<>(Comparator.naturalOrder());
        sorter.sort(arrayA);
        assertArrayEquals(new String[]{"bee", "cat", "dog", "elephant"}, arrayA);
    }

    @Test
    void testSortEmptyArray() {
        Integer[] arrayA = {};
        Sort<Integer> sorter = new MergeSort<>(Comparator.naturalOrder());
        sorter.sort(arrayA);
        assertArrayEquals(new Integer[]{}, arrayA);
    }

    @Test
    void testSortSingleElement() {
        Integer[] arrayA = {99};
        Sort<Integer> sorter = new MergeSort<>(Comparator.naturalOrder());
        sorter.sort(arrayA);
        assertArrayEquals(new Integer[]{99}, arrayA);
    }
}