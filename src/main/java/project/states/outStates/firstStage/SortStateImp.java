package project.states.outStates.firstStage;

import project.collection.MyArrayList;
import project.collection.MyList;
import project.sort.MergeSort;
import project.sort.QuickSort;
import project.sort.SortExecuter;

import java.util.Comparator;
import java.util.Random;


public class SortStateImp<T> implements SortState<T> {

    private SortExecuter<T> sortExecuter = new SortExecuter<T>();

    @Override
    public MyList<T> execute(MyList<T> list, Comparator<T> comparator) {
        MyList<T> listCopy = new MyArrayList<T>(list.toArray());
        Random random = new Random();
        int choice = random.nextDouble() >= 0.5 ? 1 : 0;
        if (choice == 0) {
            sortExecuter.setSorter(new MergeSort<T>(comparator));
            sortExecuter.executeSort(listCopy);
        } else {
            sortExecuter.setSorter(new QuickSort<T>(comparator));
            sortExecuter.executeSort(listCopy);
        }
        return listCopy;
    }
}
