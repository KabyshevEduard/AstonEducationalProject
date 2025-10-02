package org.example.collection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;


public class MyArrayListTests {

    private Comparator<Integer> comparator = Integer::compareTo;

    @Test
    public void countElementTest() {
        MyArrayList<Integer> arr =  new MyArrayList<>(1, 2, 3, 3, 4, 5, 5, 9);
        long firstCase = arr.countElement(5, 2, comparator);
        long secondCase = arr.countElement(9, 3, comparator);
        assertEquals(2L, firstCase);
        assertEquals(1L, secondCase);
    }

    @Test
    public void addTest() {
        MyList<Integer> arr = new MyArrayList<>(1, 2, 3);
        arr.add(4);
        int el = arr.get(3);
        assertEquals(4, el);
    }

    @Test
    public void getTest() {
        MyList<Integer> arr = new MyArrayList<>(1, 2, 3);
        int el = arr.get(2);
        assertEquals(3, el);
    }
}
