package datastructures.worklists;

import cse332.interfaces.worklists.LIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {

    //data fields
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private E[] stack;
    private int currentSize;
    private int capacity;
    private int nextAvaliableIndex;

    public ArrayStack() {
        stack = (E[])new Object[DEFAULT_ARRAY_SIZE];
        capacity = 10;
        currentSize = 0;
        nextAvaliableIndex = 0;
    }

    @Override
    public void add(E work) {
        if(currentSize < capacity){
            stack[nextAvaliableIndex] = work;
        }else{
           E[] newArr = (E[])new Object[capacity * 2];
            for(int i = 0; i < stack.length; i++){
                newArr[i] = stack[i];
            }
            stack = newArr;
            stack[nextAvaliableIndex] = work;
            capacity *= 2;
        }
        nextAvaliableIndex++;
        currentSize++;
    }

    @Override
    public E peek() {
        if(this.hasWork()){
            return stack[nextAvaliableIndex - 1];
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public E next() {
        if(this.hasWork()){
            E removedWork = stack[nextAvaliableIndex - 1];
            nextAvaliableIndex--;
            currentSize--;
            return removedWork;
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void clear() {
        stack = (E[])new Object[DEFAULT_ARRAY_SIZE];
        nextAvaliableIndex = 0;
        currentSize = 0;
    }
}
