package project.states.inpStates;

import project.collection.MyArrayList;
import project.collection.MyList;
import project.models.CustomEntity;
import project.models.parserr.ParseUtil;

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
