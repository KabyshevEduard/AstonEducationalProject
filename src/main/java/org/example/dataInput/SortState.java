package org.example.dataInput;

import org.example.collection.MyList;

public interface SortState<T> {

    MyList<T> execute(MyList<T> myList);

}
