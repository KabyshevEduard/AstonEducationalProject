package org.example.states.outStates.secondStage;

import org.example.collection.MyList;

import java.util.Comparator;



public class CountSearchStateImp<T> implements CountSearchState<T> {

    @Override
    public int execute(MyList<T> list, T element, Comparator<T> comparator) {

        long nElements = list.countElement(element, 2, comparator);
        System.out.println("Количество в массиве: " + nElements);
        // Search
        return 0;
    }
}
