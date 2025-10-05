package org.example.states.inpStates;

import org.example.collection.MyList;

import java.io.FileNotFoundException;

public interface InputState<T> {
    MyList<T> execute() throws FileNotFoundException;
}
