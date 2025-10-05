package org.example.states.outStates.secondStage;

import org.example.collection.MyList;


import java.io.IOException;
import java.util.Comparator;


public class SecondStageExecuter<T> {

    private WriteState<T> writeState;
    private CountSearchState<T> countSearchState;

    public SecondStageExecuter(WriteState<T> writeState, CountSearchState<T> countSearchState) {
        this.writeState = writeState;
        this.countSearchState = countSearchState;
    }

    public void executeStates(MyList<T> list, T element, Comparator<T> comparator) throws IOException {
        writeState.execute("Array.txt", list);
        int n = countSearchState.execute(list, element, comparator);
    }
}
