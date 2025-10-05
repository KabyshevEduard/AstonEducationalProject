package org.example.states.outStates.secondStage;

import org.example.collection.MyList;
import org.example.search.BinarySearch;

import java.util.Comparator;



public class CountSearchStateImp<T> implements CountSearchState<T> {

    @Override
    public int execute(MyList<T> list, T element, Comparator<T> comparator) {
        BinarySearch<T> binarySearch = new BinarySearch(comparator);

        long nElements = list.countElement(element, 2, comparator);
        System.out.println("Количество в массиве: " + nElements);

        int index = binarySearch.search(list, element);
        System.out.println("Индекс элемента: " + index);
        System.out.println();

        return index;
    }
}
