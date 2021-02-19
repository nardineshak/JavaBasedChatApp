package p2.sorts;

import java.util.Comparator;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, Comparable::compareTo);
    }


    public static <E> void sort(E[] array, Comparator<E> comparator) {
        if (array.length > 0) {
            quickSort(array, comparator, 0, array.length - 1);
        }
    }

    private static <E> void quickSort(E[] array, Comparator<E> comparator, int lo, int hi) {
        //find the pivot using the 3 median method
        int pivotIndex = median3(array, lo, hi, lo + (hi - lo) / 2, comparator);
        E pivot = array[pivotIndex];
        // swap pivot with arr[lo];
        //        swap(array, lo, pivotIndex);
        //        pivotIndex = lo;
        int i = lo;
        int j = hi; //might be just hi
        while (i <= j) {
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(array[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
            //            if(comparator.compare(array[j], pivot) > 0){
            //                j--;
            //            }else if (comparator.compare(array[i], pivot) <= 0) {
            //                i++;
            //            }else{
            //                swap(array, i, j);
            //            }
        }
        //        swap(array, i, pivotIndex);
        //        pivotIndex = i;
        //recurse
        if (lo < j) {
            quickSort(array, comparator, lo, j);
        }
        if (hi > i) {
            quickSort(array, comparator, i, hi);
        }
        //        quickSort(array, comparator, 0, pivotIndex);
        //        quickSort(array, comparator, pivotIndex, hi);
    }

    private static <E> int median3(E[] array, int loIndex, int hiIndex, int medianIndex, Comparator comparator) {
        if ((comparator.compare(array[loIndex], array[hiIndex]) < 0 && comparator.compare(array[hiIndex], array[medianIndex]) < 0)
                || (comparator.compare(array[medianIndex], array[hiIndex]) < 0 && comparator.compare(array[hiIndex], array[loIndex]) < 0)) {
            return hiIndex;
        } else if ((comparator.compare(array[hiIndex], array[loIndex])) < 0 && comparator.compare(array[loIndex], array[medianIndex]) < 0
                || (comparator.compare(array[medianIndex], array[loIndex])) < 0 && comparator.compare(array[loIndex], array[hiIndex]) < 0) {
            return loIndex;
        } else {
            return medianIndex;
        }
    }


    private static <E> void swap(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}


//average of two numbers without overflowing
//(hi + lo) >>> 1
//array[lo + (hi - lo)/2]
//        E pivot = median3(array, lo, hi, comparator);
//        int i = lo;
//        int j = hi;
//
//        if (lo + 1 <= hi) {
//            for (; ; ) {
//                while (true) {
//                    if (comparator.compare(array[++i], pivot) >= 0)
//                        break;
//                }
//                while (true) {
//                    if (comparator.compare(array[--j], pivot) <= 0)
//                        break;
//                }
//                if (i < j) {
//                    swap(array, i, j);
//                } else {
//                    break;
//                }
//            }
//            swap(array, i, hi - 1);
//
//            //lo = i + 1
//            quickSort(array, comparator, lo, i - 1);
//            quickSort(array, comparator, i + 1, hi);
//        } else {
//            for (int p = 1; p < array.length; p++) {
//                E temp = array[p];
//                int q = p;
//                while (q > 0 && comparator.compare(temp, array[q - 1]) < 0) {
//                    array[q] = array[q - 1];
//                    q--;
//                }
//                array[q] = temp;
//            }
//        }


//    private static <E> E median3(E[] array, int lo, int hi, Comparator comparator){
//        int center = lo + (hi - lo) / 2;
//        if(comparator.compare(array[center], array[lo]) < 0){
//            swap(array,lo, center);
//        }
//        if(comparator.compare(array[hi], array[lo]) < 0){
//            swap(array, lo, hi);
//        }
//        if(comparator.compare(array[hi],array[center]) < 0){
//            swap(array, center, hi);
//        }
//        swap(array, center, hi - 1);
//        return array[hi - 1];
//    }
