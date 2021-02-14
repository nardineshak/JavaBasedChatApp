package p2.sorts;

import datastructures.worklists.MinFourHeap;

import java.util.Comparator;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap<E> heap = new MinFourHeap<>(comparator);

        for (int i = 0; i < k && i < array.length; i++) {
            heap.add(array[i]);
        }

        for (int i = k; i < array.length; i++) {
            if (comparator.compare(array[i], heap.peek()) > 0) {
                heap.next();
                heap.add(array[i]);
            }
        }

        int length = k;
        if (heap.size() < k) {
            length = heap.size();
        }

        for (int i = 0; i < length; i++) {
            E temp = heap.next();
            array[i] = temp;
        }

        for (int i = k; i < array.length; i++) {
            array[i] = null;
        }
    }
}