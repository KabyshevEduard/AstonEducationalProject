package org.example.states.inpStates;

import org.example.collection.MyArrayList;
import org.example.collection.MyList;
import org.example.models.CustomEntity;

import java.util.Random;
import java.util.Scanner;

public class RandomInputState implements InputState<CustomEntity> {

    @Override
    public MyList<CustomEntity> execute() {
        MyList<CustomEntity> list = generateList();
        return list;
    }

    private <T extends CustomEntity> MyList<CustomEntity> generateList() {
        System.out.print("Введите количество элементов: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        MyList<CustomEntity> arr = new MyArrayList<CustomEntity>(n);

        for (int i = 0; i < n; i++) {
            String name = Generator.generateName();
            String surname = Generator.generateSurname();
            int age = Generator.generateAge();
            CustomEntity entity = new T.Builder().id(i).name(name).surname(surname).age(age).build();
            arr.add(entity);
        }
        return arr;
    }

    private static class Generator {

        private static String generateName() {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 5;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            return generatedString;
        }

        private static String generateSurname() {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 8;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            return generatedString;
        }

        private static int generateAge() {
            int min = 0;
            int max = 100;
            Random rnd = new Random();
            int number = min + rnd.nextInt(max - min + 1);
            return number;
        }
    }
}
