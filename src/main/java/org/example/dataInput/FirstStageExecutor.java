package org.example.dataInput;

import org.example.collection.MyList;


public class FirstStageExecutor<T> {

    private final SortState<T> sortState;

    public FirstStageExecutor(SortState<T> sortState) { this.sortState = sortState; }

    public MyList<T> executeStates(MyList<T> myList) {
        return sortState.execute(myList);
    }

}
