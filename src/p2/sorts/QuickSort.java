package p2.sorts;

import java.util.Comparator;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, Comparable::compareTo);
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        quickSort(array, comparator, 0, array.length - 1);
    }

    private static <E> void quickSort(E[] array, Comparator<E> comparator, int lo, int hi) {
        E pivot = array[lo + hi >>> 1];
        int i = lo;
        int j = hi;

        if (lo + 1 <= hi) {
            for (; ; ) {
                while (true) {
                    if (comparator.compare(array[++i], pivot) >= 0)
                        break;
                }
                while (true) {
                    if (comparator.compare(array[--j], pivot) <= 0)
                        break;
                }
                if (i < j) {
                    swap(array, i, j);
                } else {
                    break;
                }
            }
            swap(array, i, hi - 1);

            quickSort(array, comparator, lo, i - 1);
            quickSort(array, comparator, i + 1, hi);
        } else {
            for (int p = 1; p < array.length; p++) {
                E temp = array[p];
                int q = p;
                while (q > 0 && comparator.compare(temp, array[q - 1]) < 0) {
                    array[q] = array[q - 1];
                    q--;
                }
                array[q] = temp;
            }
        }
    }

    private static <E> void swap(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
