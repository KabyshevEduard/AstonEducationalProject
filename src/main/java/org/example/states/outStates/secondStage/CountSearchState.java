package org.example.states.outStates.secondStage;

import org.example.collection.MyList;

import java.util.Comparator;

public interface CountSearchState<T> {

    int execute(MyList<T> list, T element, Comparator<T> comparator);
}
