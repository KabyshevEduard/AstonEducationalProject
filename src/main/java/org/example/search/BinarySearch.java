package org.example.search;

import java.util.Comparator;

import org.example.collection.*;

public class BinarySearch<T> {
    private final Comparator<T> comparator;

    public BinarySearch(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int search(T[] array, T needle) {
        int low = 0, high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int result = comparator.compare(array[mid], needle);

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

    public int search(MyList<T> myList, T needle) {
        return search(myList.toArray(), needle);
    }
}