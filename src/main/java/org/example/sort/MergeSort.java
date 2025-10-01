package org.example.sort;

import org.example.collection.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.*;

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

    public void sort(MyArrayList<T> myArrayList) {
        T[] array = Arrays.copyOf(myArrayList.getArray(), myArrayList.getLength());
        sort(array);
        System.arraycopy(array, 0, myArrayList.getArray(), 0, array.length);
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