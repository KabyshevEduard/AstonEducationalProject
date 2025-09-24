package org.example.models.parser;

import org.example.models.CustomEntity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class ParseUtil {

    public static CustomEntity transformLine(String line) {
        return transform(line);
    }

    public static List<CustomEntity> fileInput(String pathFile) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(pathFile));
        return br
                .lines()
                .map(line -> transform(line))
                .collect(Collectors.toList());
    }

    private static <T extends CustomEntity> CustomEntity transform(String line) throws NumberFormatException {
        String lineTrimmed = line.trim();
        String[] attrArr = lineTrimmed.split("\s+|\t|,\s?");
        int id = Integer.parseInt(attrArr[0]);
        int age = Integer.parseInt(attrArr[3]);
        return new T.Builder().name(attrArr[1]).surname(attrArr[2]).age(age).id(id).build();
    }
}
