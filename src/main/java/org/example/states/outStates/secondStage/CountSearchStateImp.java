package org.example.states.outStates.secondStage;

import org.example.collection.MyList;
import org.example.search.BinarySearch;
import org.example.sort.MergeSort;
import org.example.sort.QuickSort;
import org.example.sort.SortExecuter;

import java.util.Comparator;
import java.util.Random;


public class CountSearchStateImp<T> implements CountSearchState<T> {

    @Override
    public int execute(MyList<T> list, T element, Comparator<T> comparator) {
        BinarySearch<T> binarySearch = new BinarySearch(comparator);

        long nElements = list.countElement(element, 2, comparator);
        System.out.println("Количество в массиве: " + nElements);
        System.out.println("Поиск...");

        int index = binarySearch.search(list, element);
        System.out.println("Индекс элемента: " + nElements);

        return index;
    }
}
