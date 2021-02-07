package p2.sorts;

import java.util.Comparator;
import cse332.exceptions.NotYetImplementedException;
import datastructures.worklists.MinFourHeap;

public class HeapSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<>(comparator);
        //insert into the heap to get sorted
        for (int i = 0; i < array.length; i++) {
            heap.add(array[i]);
        }
        //remove each element from the heap and insert into the original array so it is sorted
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.next();
        }
    }
}
