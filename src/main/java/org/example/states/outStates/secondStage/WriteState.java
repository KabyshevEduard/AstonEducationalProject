package org.example.states.outStates.secondStage;

import org.example.collection.MyList;

import java.io.IOException;

public interface WriteState<T> {

    void execute(String path, MyList<T> list) throws IOException;
}
