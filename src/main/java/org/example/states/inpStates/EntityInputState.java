package org.example.states.inpStates;

import org.example.collection.MyArrayList;
import org.example.collection.MyList;
import org.example.models.CustomEntity;
import org.example.models.parser.ParseUtil;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class EntityInputState implements InputState<CustomEntity> {

    @Override
    public MyList<CustomEntity> execute() throws IllegalArgumentException {
        CustomEntity entity = scanEntity();
        MyList<CustomEntity> result = new MyArrayList<CustomEntity>(entity);
        return result;
    }

    private CustomEntity scanEntity() throws IllegalArgumentException {
        System.out.print("Введите поля сущности для поиска (id, name, surname, age): ");
        Scanner scanner = new Scanner(System.in);
        String line  = scanner.nextLine();
        CustomEntity entity = ParseUtil.transformLine(line);
        return entity;
    }
}
