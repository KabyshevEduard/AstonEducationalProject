package org.example.sort;

import org.example.collection.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.*;
import java.util.function.ToIntFunction;

public class QuickSort<T> implements Sort<T> {

    private final Comparator<? super T> comparator;

    public QuickSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(T[] array) {
        if (array == null || array.length < 2) return;

        ExecutorService executor = Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors() / 2));

        try {
            quickSort(array, 0, array.length - 1, executor);
        } finally {
            executor.shutdown();
        }
    }

    @Override
    public void sort(MyList<T> myList) {
        T[] array = Arrays.copyOf(myList.getArray(), myList.getLength());
        sort(array);
        System.arraycopy(array, 0, myList.getArray(), 0, array.length);
    }

    @Override
    public void sortEven(T[] array, ToIntFunction<T> numericField) {
        if (array == null || array.length < 2) return;

        T[] evenElements = getEvenElements(array, numericField);

        ExecutorService executor = Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors() / 2));

        try {
            quickSort(evenElements, 0, evenElements.length - 1, executor);
            int evenIndex = 0;

            for (int i = 0; i < array.length; i++) {
                if (numericField.applyAsInt(array[i]) % 2 == 0) {
                    array[i] = evenElements[evenIndex++];
                }
            }

        } finally {
            executor.shutdown();
        }
    }

    public void sortEven(MyList<T> myList, ToIntFunction<T> numericField) {
        T[] array = Arrays.copyOf(myList.getArray(), myList.getLength());
        sortEven(array, numericField);
        System.arraycopy(array, 0, myList.getArray(), 0, array.length);
    }

    @SuppressWarnings("unchecked")
    private T[] getEvenElements(T[] array, ToIntFunction<T> numericField) {
        T[] evenElements = (T[]) new Object[array.length];

        int count = 0;

        for (T element : array) {
            if (numericField.applyAsInt(element) % 2 == 0) {
                evenElements[count++] = element;
            }
        }

        evenElements = Arrays.copyOf(evenElements, count);

        return evenElements;
    }

    private void quickSort(T[] array, int low, int high, ExecutorService executor) {
        if (low >= high) return;

        int pivotPos = partition(array, low, high);

        Future<?> left = executor.submit(() -> quickSort(array, low, pivotPos - 1, executor));
        Future<?> right = executor.submit(() -> quickSort(array, pivotPos + 1, high, executor));

        try {
            left.get();
            right.get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Ошибка в потоковой сортировке", e);
        }
    }

    private int partition(T[] array, int low, int high) {
        int pivotIndex = (low + high) / 2;
        T pivot = array[pivotIndex];

        swap(array, pivotIndex, high);

        int i = low;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                swap(array, i++, j);
            }
        }

        swap(array, i, high);

        return i;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}