package org.example.states.outStates;

import org.example.collection.MyArrayList;
import org.example.collection.MyList;
import org.example.sort.MergeSort;
import org.example.sort.QuickSort;
import org.example.sort.SortExecuter;

import java.util.Comparator;
import java.util.Random;
import java.util.function.ToIntFunction;

public class ExtraFirstState<T> {

    private final Comparator<T> comparator;
    private final SortExecuter<T> sortExecuter = new SortExecuter<T>();

    public ExtraFirstState(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void execute(MyList<T> list, ToIntFunction<T> numericField) {
        MyList<T> listCopy = new MyArrayList<T>(list.toArray());
        Random random = new Random();
        int choice = random.nextDouble() >= 0.5 ? 1 : 0;
        if (choice == 0) {
            sortExecuter.setSorter(new MergeSort<T>(comparator));
            sortExecuter.executeEvenSort(listCopy, numericField);
        } else {
            sortExecuter.setSorter(new QuickSort<T>(comparator));
            sortExecuter.executeEvenSort(listCopy, numericField);
        }
        System.out.println(listCopy);
    }
}
