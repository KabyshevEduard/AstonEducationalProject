package org.example.sort;

import org.example.collection.MyList;

import java.util.Comparator;
import java.util.function.ToIntFunction;

public class SortExecuter<T> {

    private Sort<T> sorter;

    public void setSorter(Sort<T> sorter) {
        this.sorter = sorter;
    }

    public void executeSort(MyList<T> myList) {
        sorter.sort(myList);
    }

    public void executeEvenSort(MyList<T> myList, ToIntFunction<T> numericField) {
        sorter.sortEven(myList, numericField);
    }
}
