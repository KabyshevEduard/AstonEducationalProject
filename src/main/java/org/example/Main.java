package org.example;

import org.example.collection.MyList;
import org.example.models.CustomEntity;
import org.example.models.comparators.AgeComparator;
import org.example.models.comparators.NameComparator;
import org.example.models.comparators.SurnameComparator;
import org.example.states.inpStates.FileInputState;
import org.example.states.inpStates.InputStateExecuter;
import org.example.states.inpStates.RandomInputState;
import org.example.states.inpStates.WriteInputState;
import org.example.states.outStates.ExitState;

import java.io.FileNotFoundException;
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
                inputStateExecuter.setState(new WriteInputState());

                tryInput1:
                while (true) {
                    try {
                        myList = inputStateExecuter.executeState();
                        break tryInput1;
                    }
                    catch (FileNotFoundException | IllegalArgumentException | InputMismatchException e) {
                        System.out.println("Вы ввели не то, что ожидалось");
                        System.out.println("Введите заного");
                        continue tryInput1;
                    }
                }
                // Actions
            }

            if (choice == 2) {
                inputStateExecuter.setState(new FileInputState());

                tryInput2:
                while (true) {
                    try {
                        myList = inputStateExecuter.executeState();
                        break tryInput2;
                    }
                    catch (FileNotFoundException | IllegalArgumentException | InputMismatchException e) {
                        System.out.println("Вы ввели не то, что ожидалось");
                        System.out.println("Введите заного");
                        continue tryInput2;
                    }
                }
                // Actions
            }

            if (choice == 3) {
                inputStateExecuter.setState(new RandomInputState());

                tryInput3:
                while (true) {
                    try {
                        myList = inputStateExecuter.executeState();
                        break tryInput3;
                    }
                    catch (FileNotFoundException | IllegalArgumentException | InputMismatchException e) {
                        System.out.println("Вы ввели не то, что ожидалось");
                        System.out.println("Введите заного");
                        continue tryInput3;
                    }
                    // Actions
                }
            }
        }
    }
}