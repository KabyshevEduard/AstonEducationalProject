package project.states.outStates.firstStage;

import project.collection.MyList;

import java.util.Comparator;

public interface SortState<T> {

    MyList<T> execute(MyList<T> list, Comparator<T> comparator);
}
