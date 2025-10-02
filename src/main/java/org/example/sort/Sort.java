package org.example.sort;

import java.util.function.ToIntFunction;

public interface Sort<T> {
    void sort(T[] array);
    void sortEven(T[] array, ToIntFunction<T> numericField);
}