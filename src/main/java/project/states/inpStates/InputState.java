package project.states.inpStates;

import project.collection.MyList;

import java.io.FileNotFoundException;

public interface InputState<T> {
    MyList<T> execute() throws FileNotFoundException;
}
