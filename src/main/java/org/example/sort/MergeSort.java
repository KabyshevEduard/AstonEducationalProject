package org.example.sort;

import java.util.Arrays;
import java.util.Comparator;

import org.example.collection.*;

public class MergeSort<T> implements Sort<T> {

    private final Comparator<? super T> comparator;

    public MergeSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(T[] array) {
        if (array == null || array.length < 2) return;

        T[] sorted = mergeSort(array);
        System.arraycopy(sorted, 0, array, 0, array.length);
    }

    public void sort(MyArrayList<T> myArrayList) {
        sort(myArrayList.getArray());
    }


    private T[] mergeSort(T[] arrayA) {
        if (arrayA == null) return null;
        if (arrayA.length < 2) return arrayA;

        int mid = arrayA.length / 2;
        T[] arrayB = Arrays.copyOfRange(arrayA, 0, mid);
        T[] arrayC = Arrays.copyOfRange(arrayA, mid, arrayA.length);

        arrayB = mergeSort(arrayB);
        arrayC = mergeSort(arrayC);

        return mergeArray(arrayB, arrayC);
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