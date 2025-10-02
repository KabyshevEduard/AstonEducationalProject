package org.example.states.outStates.secondStage;

import org.example.collection.MyList;

import java.io.FileWriter;
import java.io.IOException;

public class WriteStateImp<T> implements WriteState<T> {

    @Override
    public void execute(String path, MyList<T> list) throws IOException {
        writeIntoFile(path, list);
    }

    private void writeIntoFile(String path, MyList<T> list) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(list.toString() + "\n");
        fw.flush();
        fw.close();
        System.out.println("Массив записан");
    }
}
