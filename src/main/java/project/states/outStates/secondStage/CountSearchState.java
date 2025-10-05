package project.states.outStates.secondStage;

import project.collection.MyList;

import java.util.Comparator;

public interface CountSearchState<T> {

    int execute(MyList<T> list, T element, Comparator<T> comparator);
}
