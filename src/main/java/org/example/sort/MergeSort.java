package org.example.sort;

import org.example.collection.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.*;
import java.util.function.ToIntFunction;

public class MergeSort<T> implements Sort<T> {

    private final Comparator<? super T> comparator;

    public MergeSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(T[] array) {
        if (array == null || array.length < 2) return;

        ExecutorService executor = Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors() / 2));

        try {
            T[] sorted = mergeSort(array, executor);
            System.arraycopy(sorted, 0, array, 0, array.length);
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
            T[] sortedEvens = mergeSort(evenElements, executor);
            int evenIndex = 0;

            for (int i = 0; i < array.length; i++) {
                if (numericField.applyAsInt(array[i]) % 2 == 0) {
                    array[i] = sortedEvens[evenIndex++];
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

    private T[] mergeSort(T[] arrayA, ExecutorService executor) {
        if (arrayA == null) {
            return null;
        } else if (arrayA.length < 2) {
            return arrayA;
        } else {
            int mid = arrayA.length / 2;
            T[] left = Arrays.copyOfRange(arrayA, 0, mid);
            T[] right = Arrays.copyOfRange(arrayA, mid, arrayA.length);

            Future<T[]> futureLeft = executor.submit(() -> mergeSort(left, executor));
            Future<T[]> futureRight = executor.submit(() -> mergeSort(right, executor));

            try {
                T[] sortedLeft = futureLeft.get();
                T[] sortedRight = futureRight.get();

                return mergeArray(sortedLeft, sortedRight);
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Ошибка в потоковой сортировке", e);
            }
        }
    }

    private T[] mergeArray(T[] arrayA, T[] arrayB) {
        @SuppressWarnings("unchecked")
        T[] arrayC = (T[]) new Object[arrayA.length + arrayB.length];
        int positionA = 0, positionB = 0, index = 0;

        while (positionA < arrayA.length && positionB < arrayB.length) {
            if (comparator.compare(arrayA[positionA], arrayB[positionB]) <= 0) {
                arrayC[index++] = arrayA[positionA++];
            } else {
                arrayC[index++] = arrayB[positionB++];
            }
        }

        while (positionA < arrayA.length) {
            arrayC[index++] = arrayA[positionA++];
        }

        while (positionB < arrayB.length) {
            arrayC[index++] = arrayB[positionB++];
        }

        return arrayC;
    }
}