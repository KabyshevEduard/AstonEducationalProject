package project.states.inpStates;

import project.collection.MyList;
import project.models.CustomEntity;

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
