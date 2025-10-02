package org.example.states.outStates.firstStage;

import org.example.collection.MyList;
import org.example.sort.MergeSort;
import org.example.sort.QuickSort;
import org.example.sort.SortExecuter;

import java.util.Comparator;
import java.util.Random;


public class SortStateImp<T> implements SortState<T> {

    private SortExecuter<T> sortExecuter = new SortExecuter<T>();

    @Override
    public MyList<T> execute(MyList<T> list, Comparator<T> comparator) {
        Random random = new Random();
        int choice = random.nextDouble() >= 0.5 ? 1 : 0;
        if (choice == 0) {
            sortExecuter.setSorter(new MergeSort<T>(comparator));
            sortExecuter.executeSort(list);
        } else {
            sortExecuter.setSorter(new QuickSort<T>(comparator));
            sortExecuter.executeSort(list);
        }
        System.out.println("Сортировка...");
        return list;
    }
}
