package project.states.outStates.secondStage;

import project.collection.MyList;

import java.io.IOException;

public interface WriteState<T> {

    void execute(String path, MyList<T> list) throws IOException;
}
