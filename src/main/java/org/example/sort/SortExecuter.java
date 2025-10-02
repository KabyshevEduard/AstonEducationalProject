package org.example.sort;

import org.example.collection.MyList;

public class SortExecuter<T> {

    private Sort<T> sorter;

    public void setSorter(Sort<T> sorter) {
        this.sorter = sorter;
    }

    public void executeSort(MyList<T> myList) {
        sorter.sort(myList);
    }
}
