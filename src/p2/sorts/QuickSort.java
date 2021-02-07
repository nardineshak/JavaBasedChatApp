package p2.sorts;

import java.util.Comparator;

import cse332.exceptions.NotYetImplementedException;
import datastructures.worklists.MinFourHeap;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        if(array.length > 1) {
            quickSort(array, comparator, 0, array.length - 1);
        }
    }

    private static <E> void quickSort(E[] array, Comparator<E> comparator, int lo, int hi){
        if(lo + 1 <= hi){
            int pivotIndex = median3(array, comparator, lo, hi);
            swap(array, lo, pivotIndex);
            pivotIndex = 0;
            int i = lo + 1;
            int j = hi;
            while (i < j) {
                if (comparator.compare(array[j], array[pivotIndex]) > 0) {
                    j--;
                } else if (comparator.compare(array[i], array[pivotIndex]) <= 0) {
                    i++;
                } else {
                    swap(array, i, j);
                    i++;
                    j--;
                }
            }
            swap(array, pivotIndex, i);
            pivotIndex = i;
            quickSort(array, comparator, lo, pivotIndex - 1);
            quickSort(array, comparator, pivotIndex + 1, hi);
        }
    }

    private static <E> void swap(E[] array, int index1, int index2){
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static <E> int median3(E[] array,Comparator comparator, int lo, int hi){
        int center = (lo + hi) / 2;
//        if((comparator.compare(array[center], array[lo]) < 0 &&  comparator.compare(array[lo], array[hi]) < 0)
//        || (comparator.compare(array[hi], array[lo]) < 0 &&  comparator.compare(array[lo], array[center]) < 0)){
//            medianIndex = lo;
//        }else if((comparator.compare(array[lo], array[center]) < 0 &&  comparator.compare(array[center], array[hi]) < 0)
//                || (comparator.compare(array[hi], array[center]) < 0 &&  comparator.compare(array[center], array[lo]) < 0)){
//            medianIndex = center;
//        }else{
//            medianIndex = hi;
//        }
        MinFourHeap heap = new MinFourHeap(comparator);
        heap.add(array[lo]);
        heap.add(array[hi]);
        heap.add(array[center]);
        E[] arr = (E[]) new Object[3];
        for(int i = 0; i < 3; i++){
            arr[i] = (E) heap.next();
        }
        E medianVal = arr[1];
        int medianIndex;
        if(array[lo] == medianVal){
            medianIndex = lo;
        }else if (array[hi] == medianVal){
            medianIndex = hi;
        }else{
            medianIndex = center;
        }
        return medianIndex;
    }
}
