package org.example.states.outStates.firstStage;

import org.example.collection.MyList;

public interface SortState<T> {

    MyList<T> execute(MyList<T> list);
}
