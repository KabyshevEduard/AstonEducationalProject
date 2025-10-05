package project.states.inpStates;

import project.collection.MyArrayList;
import project.collection.MyList;
import project.models.CustomEntity;
import project.models.parserr.ParseUtil;

import java.util.Scanner;

public class WriteInputState implements InputState<CustomEntity> {

    @Override
    public MyList<CustomEntity> execute() throws IllegalArgumentException {
        MyList<CustomEntity> myList = inputData();
        return myList;
    }

    private MyList<CustomEntity> inputData() throws IllegalArgumentException {
        System.out.print("Введите число элементов: ");
        Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();

        MyList<CustomEntity> arr = new MyArrayList<CustomEntity>(n);

        for (int i = 0; i < n; i++) {
            System.out.print("Введите id, name, surname, age: ");
            Scanner scanner1 = new Scanner(System.in);
            String line = scanner1.nextLine();
            CustomEntity entity = ParseUtil.transformLine(line);
            arr.add(entity);
        }
        return arr;
    }
}
