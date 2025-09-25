package org.example.models.parser;

import org.example.collection.MyArrayList;
import org.example.collection.MyList;
import org.example.models.CustomEntity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.IntStream;


public class ParseUtil {

    // Parse line to object
    public static CustomEntity transformLine(String line) {
        return transform(line);
    }

    // Parse txt file to list of objects
    public static MyList<CustomEntity> fileInput(String pathFile) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(pathFile));

        // Extra task 3 (Add elements to custom collection by stream)
        MyList<CustomEntity> arr = new MyArrayList<CustomEntity>(100);
        br.lines().forEach(el -> arr.add(transform(el)));

        return arr;
    }

    // Parse string line to object
    private static <T extends CustomEntity> CustomEntity transform(String line) throws NumberFormatException {
        String lineTrimmed = line.trim();
        String[] attrArr = lineTrimmed.split("\s+|\t|,\s?");
        int id = Integer.parseInt(attrArr[0]);
        int age = Integer.parseInt(attrArr[3]);
        return new T.Builder().name(attrArr[1]).surname(attrArr[2]).age(age).id(id).build();
    }
}
