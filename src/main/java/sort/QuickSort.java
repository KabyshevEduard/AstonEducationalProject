package sort;

import java.util.Comparator;

public class QuickSort<T> implements Sort<T> {

    private final Comparator<? super T> comparator;

    public QuickSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(T[] array) {
        if (array == null || array.length < 2) return;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int low, int high) {
        if (low >= high) return;

        int pivotPos = partition(array, low, high);
        quickSort(array, low, pivotPos - 1);
        quickSort(array, pivotPos + 1, high);
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