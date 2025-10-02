package org.example.states.outStates.firstStage;

import org.example.collection.MyList;

import java.util.Comparator;


public class FirstStageExecuter<T> {

    private SortState<T> sortState;

    public FirstStageExecuter(SortState<T> sortState) {
        this.sortState = sortState;
    }

    public MyList<T> executeStates(MyList<T> list, Comparator<T> comparator) {
        return this.sortState.execute(list, comparator);
    }
}
