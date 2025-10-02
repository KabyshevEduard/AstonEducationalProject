package org.example.parser;

import org.example.collection.MyList;
import org.example.models.CustomEntity;
import org.example.models.parserr.ParseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;

public class ParseUtilTests {

    @Test
    public void parseFileTest() {
        try {
            MyList<CustomEntity> arr = ParseUtil.fileInput("testData.txt");
            CustomEntity ent1 = arr.get(0);
            CustomEntity ent2 = arr.get(1);

            // First entity
            Assertions.assertEquals("Ivan", ent1.getName());
            Assertions.assertEquals("Ivanov", ent1.getSurname());
            Assertions.assertEquals(50, ent1.getAge());

            // Second entity
            Assertions.assertEquals("Oleg", ent2.getName());
            Assertions.assertEquals(55, ent2.getAge());
            Assertions.assertEquals("Olegov", ent2.getSurname());

        } catch (FileNotFoundException e) {

        }
    }

    @Test
    public void parseLineTest() {
        String line = "1, Ivan  Ivanov 10";
        CustomEntity ent = ParseUtil.transformLine(line);
        Assertions.assertEquals(1, ent.getId());
        Assertions.assertEquals("Ivan", ent.getName());
        Assertions.assertEquals("Ivanov", ent.getSurname());
        Assertions.assertEquals(10, ent.getAge());
    }
}
