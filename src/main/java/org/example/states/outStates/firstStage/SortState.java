package org.example.states.outStates.firstStage;

import org.example.collection.MyList;

import java.util.Comparator;

public interface SortState<T> {

    MyList<T> execute(MyList<T> list, Comparator<T> comparator);
}
