package org.example.collection;

public interface MyList<T> {

    void add(T item);

    T get(int index);

    boolean remove(int index);

    void addAll(T[] items);

    int getLength();

    T[] getArray();

    long countElement(T item, int nThreads);
}
