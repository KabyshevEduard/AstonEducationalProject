package org.example;

import org.example.collection.MyList;
import org.example.models.CustomEntity;
import org.example.models.comparators.AgeComparator;
import org.example.models.comparators.NameComparator;
import org.example.models.comparators.SurnameComparator;
import org.example.states.inpStates.*;
import org.example.states.outStates.ExitState;
import org.example.states.outStates.firstStage.FirstStageExecuter;
import org.example.states.outStates.firstStage.SortStateImp;
import org.example.states.outStates.secondStage.CountSearchStateImp;
import org.example.states.outStates.secondStage.SecondStageExecuter;
import org.example.states.outStates.secondStage.WriteStateImp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    private static InputStateExecuter inputStateExecuter = new InputStateExecuter();
    private static final Comparator<CustomEntity> comparator = new NameComparator()
            .thenComparing(new SurnameComparator())
            .thenComparing(new AgeComparator());
    private static final ExitState exitState = new ExitState();
    private static MyList<CustomEntity> myList;


    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите действие");
            System.out.println("1. Ввести массив\n2. Выбрать файл с массивом\n3. Случайно инициализировать массив\n4. Выход");
            System.out.print("Действие: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 4) {
                exitState.exit();
                break;
            }

            if (choice == 1) {
                CustomEntity element = getCustomEntity(new WriteInputState());
                pipeline2(element);
            }

            if (choice == 2) {
                CustomEntity element = getCustomEntity(new FileInputState());
                pipeline2(element);
            }

            if (choice == 3) {
                CustomEntity element = getCustomEntity(new RandomInputState());
                pipeline2(element);
            }
        }
    }

    private static CustomEntity getCustomEntity(InputState state) {
        inputStateExecuter.setState(state);

        tryInput1:
        while (true) {
            try {
                myList = inputStateExecuter.executeState();
                break tryInput1;
            } catch (FileNotFoundException | IllegalArgumentException | InputMismatchException e) {
                System.out.println("Вы ввели не то, что ожидалось");
                System.out.println("Введите заного");
                continue tryInput1;
            }
        }

        inputStateExecuter.setState(new EntityInputState());
        CustomEntity el = null;

        tryInput2:
        while (true) {
            try {
                MyList<CustomEntity> element = inputStateExecuter.executeState();
                el = element.get(0);
                break tryInput2;
            } catch (FileNotFoundException | IllegalArgumentException | InputMismatchException e) {
                System.out.println("Вы ввели не то, что ожидалось");
                System.out.println("Введите заного");
                continue tryInput2;
            }
        }

        return el;
    }

    private static void pipeline2(CustomEntity element) {
        FirstStageExecuter<CustomEntity> firstStageExecuter = new FirstStageExecuter<CustomEntity>(new SortStateImp<CustomEntity>());

        SecondStageExecuter<CustomEntity> secondStageExecuter = new SecondStageExecuter<CustomEntity>(
                new WriteStateImp<CustomEntity>(),
                new CountSearchStateImp<CustomEntity>()
        );
        myList = firstStageExecuter.executeStates(myList);

        try {
            secondStageExecuter.executeStates(myList, element);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}