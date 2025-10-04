package org.example.dataInput;

import org.example.collection.MyList;
import org.example.sort.MergeSort;
import org.example.sort.QuickSort;
import org.example.sort.SortExecuter;

import java.util.Comparator;

public class SortStateImp<T> implements SortState<T>{

    private final Comparator<? super T> comparator;
    private int turn = 1;  // turn == 1 - quick sort
                           // turn == 2 - merge sort

    public SortStateImp(Comparator<? super T> comparator) {

        this.comparator = comparator;
    }

    @Override
    public MyList<T> execute(MyList<T> myList) {

        System.out.println("Выполняется сортировка...");

        return doSort(myList);
    }

    private MyList<T> doSort(MyList<T> myList) {
        SortExecuter<T> sortExecuter = new SortExecuter<>();
        if (turn < 2){
            sortExecuter.setSorter(new QuickSort<>(comparator));
            sortExecuter.executeSort(myList);
            turn++;
        } else {
            sortExecuter.setSorter(new MergeSort<>(comparator));
            sortExecuter.executeSort(myList);
            turn = 1;
        }


        return myList;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
