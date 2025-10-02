package org.example.sort;

import org.example.collection.MyList;

import java.util.function.ToIntFunction;

public interface Sort<T> {
    void sort(T[] array);
    void sortEven(T[] array, ToIntFunction<T> numericField);
    void sort(MyList<T> myList);
}