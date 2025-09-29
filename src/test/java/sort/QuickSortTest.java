package org.example.sort;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testSortIntegers() {
        Integer[] arrayA = {5, 3, 8, 1, 2};
        Sort<Integer> sorter = new QuickSort<>(Comparator.naturalOrder());
        sorter.sort(arrayA);
        assertArrayEquals(new Integer[]{1, 2, 3, 5, 8}, arrayA);
    }

    @Test
    void testSortStrings() {
        String[] arrayA = {"banana", "apple", "cherry"};
        Sort<String> sorter = new QuickSort<>(Comparator.naturalOrder());
        sorter.sort(arrayA);
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, arrayA);
    }

    @Test
    void testSortEmptyArray() {
        Integer[] arrayA = {};
        Sort<Integer> sorter = new QuickSort<>(Comparator.naturalOrder());
        sorter.sort(arrayA);
        assertArrayEquals(new Integer[]{}, arrayA);
    }

    @Test
    void testSortSingleElement() {
        Integer[] arrayA = {42};
        Sort<Integer> sorter = new QuickSort<>(Comparator.naturalOrder());
        sorter.sort(arrayA);
        assertArrayEquals(new Integer[]{42}, arrayA);
    }
}