package org.example.states.outStates.firstStage;

import org.example.collection.MyList;


public class SortStateImp<T> implements SortState<T> {



    @Override
    public MyList<T> execute(MyList<T> list) {
        // doSort(list);
        return list;
    }

    private MyList<T> doSort(MyList<T> list) {
        return null;
    }
}
