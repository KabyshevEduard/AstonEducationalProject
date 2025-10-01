package org.example.sort;

import org.example.collection.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.*;

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

    public void sort(MyArrayList<T> myArrayList) {
        T[] array = Arrays.copyOf(myArrayList.getArray(), myArrayList.getLength());
        sort(array);
        System.arraycopy(array, 0, myArrayList.getArray(), 0, array.length);
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
            if (comparator.compare(array[j], pivot) <= 0) swap(array, i++, j);
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