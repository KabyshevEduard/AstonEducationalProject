package org.example.states.outStates.secondStage;

import org.example.collection.MyList;

public class CountSearchStateImp<T> implements CountSearchState<T> {

    @Override
    public int execute(MyList<T> list, T element) {

        long nElements = list.countElement(element, 2);
        System.out.println("Количество в массиве: " + nElements);
        // Search
        return 0;
    }
}
