package project;

import project.collection.MyList;
import project.models.CustomEntity;
import project.models.comparators.AgeComparator;
import project.models.comparators.NameComparator;
import project.models.comparators.SurnameComparator;
import project.states.inpStates.*;
import project.states.outStates.ExitState;
import project.states.outStates.ExtraFirstState;
import project.states.outStates.firstStage.FirstStageExecuter;
import project.states.outStates.firstStage.SortStateImp;
import project.states.outStates.secondStage.CountSearchStateImp;
import project.states.outStates.secondStage.SecondStageExecuter;
import project.states.outStates.secondStage.WriteStateImp;

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
    private static final ExtraFirstState<CustomEntity> extraFirstState = new ExtraFirstState<>(new AgeComparator());
    private static final ExitState exitState = new ExitState();
    private static MyList<CustomEntity> myList;


    public static void main(String[] args) {

        while (true) {

            Integer choice = null;
            actionChoice:
            while (choice == null) {
                try {
                    System.out.println("Выберите действие");
                    System.out.println("1. Ввести массив\n2. Выбрать файл с массивом\n3. Случайно инициализировать массив\n4. Выход\n5. Дополнительное задание: остортировать по четным и нечетным\n");
                    System.out.print("Действие: ");
                    Scanner scanner = new Scanner(System.in);
                    choice = scanner.nextInt();
                    break actionChoice;
                } catch (InputMismatchException e) {
                    continue actionChoice;
                }
            }

            if (choice == 4) {
                exitState.exit();
                break;
            }

            if (choice == 1) {
                getCustomEntity(new WriteInputState());
                pipeline2();
            }

            if (choice == 2) {
                getCustomEntity(new FileInputState());
                pipeline2();
            }

            if (choice == 3) {
                getCustomEntity(new RandomInputState());
                System.out.println(myList);
                pipeline2();
            }

            if (choice == 5) {
                System.out.println("Выберите как инициализировать массив:");
                System.out.println("1. Ввести\n2. Выбрать файл\n3. Случайно");
                Scanner sc = new Scanner(System.in);
                int choice1 = sc.nextInt();
                if (choice1 == 1) {
                    getCustomEntity(new WriteInputState());
                }
                if (choice1 == 2) {
                    getCustomEntity(new FileInputState());
                }
                if (choice1 == 3) {
                    getCustomEntity(new RandomInputState());
                }
                extraFirstState.execute(myList, CustomEntity::getAge);
            }
        }
    }

    private static void getCustomEntity(InputState state) {
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
    }

    private static void pipeline2() {
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

        FirstStageExecuter<CustomEntity> firstStageExecuter = new FirstStageExecuter<CustomEntity>(new SortStateImp<CustomEntity>());

        SecondStageExecuter<CustomEntity> secondStageExecuter = new SecondStageExecuter<CustomEntity>(
                new WriteStateImp<CustomEntity>(),
                new CountSearchStateImp<CustomEntity>()
        );
        myList = firstStageExecuter.executeStates(myList, comparator);

        try {
            secondStageExecuter.executeStates(myList, el, comparator);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}