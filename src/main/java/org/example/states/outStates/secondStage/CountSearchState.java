package org.example.states.outStates.secondStage;

import org.example.collection.MyList;

public interface CountSearchState<T> {

    int execute(MyList<T> list, T element);
}
