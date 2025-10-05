package org.example.states.inpStates;

import org.example.collection.MyList;
import org.example.models.CustomEntity;

import java.io.FileNotFoundException;

public class InputStateExecuter {

    private InputState inputState;

    public void setState(InputState inputState) {
        this.inputState = inputState;
    }

    public MyList<CustomEntity> executeState() throws FileNotFoundException {
        return this.inputState.execute();
    }
}
