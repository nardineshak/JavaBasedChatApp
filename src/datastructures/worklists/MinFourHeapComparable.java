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
        return (this.size != 0);
    }

    //nardin
    @Override
    public void add(E work) {
        //if the array is full double the size of it
        if(size == capacity){
            capacity *= 2;
            E[] temp = (E[]) new Comparable[capacity];
            for(int i = 0; i < data.length; i++){
                temp[i] = data[i];
            }
            data = temp;
        }
        if(this.size == 0){
            data[0] = work;
            root = work;
        }else{
            int index = percolateUp(size, work);
            data[index] = work;
            if(index == 0){
                root = data[0];
            }
        }
        size++;
    }

    public int percolateUp(int index, E work){
        while(index > 0 && (work.compareTo(data[index / 4]) < 0)) {
            data[index] = data[index / 4];
            index /= 4;
        }
        return index;
    }

    @Override
    public E peek() {
        if(this.hasWork() == false){
            throw new NoSuchElementException();
        }
        return root;
    }

    //julian
    @Override
    public E next() { throw new NotYetImplementedException();
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
