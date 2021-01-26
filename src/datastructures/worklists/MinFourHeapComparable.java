package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.PriorityWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeapComparable<E extends Comparable<E>> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    private int size;
    private E root;
    private int capacity = 10;

    public MinFourHeapComparable() {
        data = (E[])new Comparable[capacity];
        size = 0;
    }

    @Override
    public boolean hasWork() {
        return this.size > 0;
    }


    @Override
    public void add(E work) {
        //if the array is full double the size of it
        if (size == capacity){
            capacity *= 2;
            E[] temp = (E[]) new Comparable[capacity];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
        if (this.size == 0) {
            data[0] = work;
            root = work;
        } else {
            int index = percolateUp(size, work);
            data[index] = work;
            if (index == 0) {
                root = data[0];
            }
        }
        size++;
    }

    public int percolateUp(int index, E work) {
        while(index > 0 && (work.compareTo(data[(index - 1) / 4]) < 0)) {
            data[index] = data[(index - 1) / 4];
            index = (index - 1) / 4;
        }
        return index;
    }

    @Override
    public E peek() {
        if (!this.hasWork()) {
            throw new NoSuchElementException();
        } else {
            return root;
        }
    }

    @Override
    public E next() {
        E answer = root;
        if (!this.hasWork()) {
            throw new NoSuchElementException();
        } else {
            int hole = percolateDown(0, data[size - 1]);
            data[hole] = data[size - 1];
            root = data[0];
            size--;
        }
        return answer;
    }

    public int percolateDown(int hole, E work) {
        while ((4 * hole) < size - 1) {
            int c1 = (4 * hole + 1);
            int c2 = c1 + 1;
            int c3 = c2 + 1;
            int c4 = c3 + 1;
            int minChild = c1;
            for (int i = c1; i <= c4 && i < size; i++) {
                if (data[i] != null && data[i].compareTo(data[minChild]) < 0) {
                    minChild = i;
                }
            }

            if (data[minChild].compareTo(work) < 0) {
                data[hole] = data[minChild];
                hole = minChild;
            } else {
                break;
            }
        }
        return hole;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        data = (E[]) new Comparable[10];
        root = null;
        size = 0;
    }
}
