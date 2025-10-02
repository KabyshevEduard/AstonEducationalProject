package org.example.parser;

import org.example.collection.MyList;
import org.example.models.CustomEntity;
import org.example.models.parser.ParseUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ParseUtilTests {

    @Test
    public void parseFileTest() {
        try {
            MyList<CustomEntity> arr = ParseUtil.fileInput("testData.txt");
            CustomEntity ent1 = arr.get(0);
            CustomEntity ent2 = arr.get(1);

            // First entity
            Assert.assertEquals("Ivan", ent1.getName());
            Assert.assertEquals("Ivanov", ent1.getSurname());
            Assert.assertEquals(50, ent1.getAge());

            // Second entity
            Assert.assertEquals("Oleg", ent2.getName());
            Assert.assertEquals(55, ent2.getAge());
            Assert.assertEquals("Olegov", ent2.getSurname());

        } catch (FileNotFoundException e) {

        }
    }

    @Test
    public void parseLineTest() {
        String line = "1, Ivan  Ivanov 10";
        CustomEntity ent = ParseUtil.transformLine(line);
        Assert.assertEquals(1, ent.getId());
        Assert.assertEquals("Ivan", ent.getName());
        Assert.assertEquals("Ivanov", ent.getSurname());
        Assert.assertEquals(10, ent.getAge());
    }
}
