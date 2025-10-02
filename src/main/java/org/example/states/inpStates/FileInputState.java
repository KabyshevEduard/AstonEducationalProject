package org.example.states.inpStates;

import org.example.collection.MyList;
import org.example.models.CustomEntity;
import org.example.models.parserr.ParseUtil;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInputState implements InputState<CustomEntity> {

    @Override
    public MyList<CustomEntity> execute() throws FileNotFoundException {
        MyList<CustomEntity> myList = fileRead();
        return myList;
    }

    private MyList<CustomEntity> fileRead() throws FileNotFoundException {
        System.out.print("Введите путь к файлу: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        MyList<CustomEntity> arr = ParseUtil.fileInput(path);
        return arr;
    }
}
