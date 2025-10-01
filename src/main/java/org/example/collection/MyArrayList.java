package org.example.collection;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.lang.Math.round;

// Extra task 3
// Custom array with stream
public class MyArrayList<T> implements MyList<T> {
    private int size;
    private Object[] bucket;
    private int currentSize = 0;

    public MyArrayList(int size) {
        this.size = size;
        bucket = new Object[size];
    }

    public MyArrayList(T... elements) {
        size = elements.length + (int) (0.7 * elements.length);
        currentSize = elements.length;
        bucket = new Object[size];
        for (int i = 0; i < elements.length; i++) {
            bucket[i] = elements[i];
        }
    }

    @Override
    public int getLength() {
        return currentSize;
    }

    // Add element to end
    @Override
    public void add(T item) {
        if (size - currentSize < 1) {
            Object[] oldBucket = bucket;
            bucket = new Object[3 * size];
            for (int i = 0; i < oldBucket.length; ++i) {
                bucket[i] = oldBucket[i];
            }
        }
        bucket[currentSize] = item;
        ++currentSize;
    }

    // Get element by index
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (!checkIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) bucket[index];
    }

    // Remove element by index
    @Override
    public boolean remove(int index) {
        if (!checkIndex(index)) {
            return false;
        }
        for (int i = index; i < currentSize; i++) {
            bucket[i] = bucket[i + 1];
        }
        --currentSize;
        return true;
    }

    // Add array of elements to end
    @Override
    public void addAll(T[] items) {
        if (size - currentSize < items.length) {
            Object[] oldBucket = bucket;
            bucket = new Object[3 * size];
            for (int i = 0; i < oldBucket.length; ++i) {
                bucket[i] = oldBucket[i];
            }
            for (int i = oldBucket.length; i < oldBucket.length + items.length; ++i) {
                bucket[i] = items[i - oldBucket.length];
            }
            currentSize = oldBucket.length + items.length;
        } else {
            for (int i = currentSize; i < currentSize + items.length; ++i) {
                bucket[i] = items[i - currentSize];
            }
            currentSize += items.length;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] getArray() {
        return (T[]) bucket;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) Arrays.copyOf(bucket, currentSize, bucket.getClass());
    }

    // Extra task 4
    @Override
    public long countElement(T item, int nThreads) {
        int pivot = (int) round((double) currentSize / nThreads);
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        long resultCount = 0L;
        for (int i = 0; i < nThreads; ++i) {
            Future result = executor.submit(taskCount(i, pivot, item));
            try {
                resultCount += (long) result.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }
        executor.shutdown();
        return resultCount;
    }

    public Stream<T> stream() {
        return Arrays.stream(this.bucket).limit(currentSize).map(t -> (T) t);
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < currentSize;
    }

    private Callable<Long> taskCount(int i, int pivot, T el) {
        return () -> {
            Object[] subArr;
            subArr = Arrays.copyOfRange(bucket, i * pivot, i * pivot + pivot);

            // Boundary condition
            if (i * pivot + pivot > currentSize) {
                subArr = Arrays.copyOfRange(bucket, i * pivot, currentSize);
            }
            Long count = Stream.of(subArr).map(t -> (T) t).filter(el::equals).count();
            return count;
        };
    }

    @Override
    public String toString() {
        Object[] toOutput = Arrays.copyOfRange(bucket, 0, currentSize);
        return Arrays.toString(toOutput);
    }
}