package org.example.search;

import java.util.Comparator;

public class BinarySearch<T> {
    private final Comparator<T> comparator;

    public BinarySearch(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int search(T[] array, T key) {
        int low = 0, high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int result = comparator.compare(array[mid], key);

            if (result < 0) {
                low = mid + 1;
            } else if (result > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
            return -1;
    }
}