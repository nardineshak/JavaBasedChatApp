package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E extends Comparable> extends FixedSizeFIFOWorkList<E> {

    private E[] circularArray;
    private int head;
    private int tail;
    private int size;

    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        circularArray = (E[]) new Comparable[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void add(E work) {
        if (!this.isFull()) {
            if (size == 0 && tail == 0 && head == 0) {
                circularArray[0] = work;
            } else {
                tail = (tail + 1) % super.capacity();
                circularArray[tail] = work;
            }
            size++;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public E peek() {
        if (this.hasWork()) {
            return circularArray[head];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E peek(int i) {
        if (!this.hasWork()) {
            throw new NoSuchElementException();
        } else if (i < 0 || i >= super.capacity()) {
            throw new IndexOutOfBoundsException();
        } else {
            return circularArray[(head + i) % super.capacity()];
        }
    }

    @Override
    public E next() {
        if (this.hasWork()) {
            E removedWork = circularArray[head];
            head = (head + 1) % super.capacity();
            size--;
            return removedWork;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void update(int i, E value) {
        if (!this.hasWork()) {
            throw new NoSuchElementException();
        } else if (i < 0 || i >= super.capacity()) {
            throw new IndexOutOfBoundsException();
        } else {
            circularArray[i] = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        int val = 0;
        if (other != null) {
            int shortLength;
            int longLength;
            if (other.size() < this.size) {
                shortLength = other.size();
            } else {
                shortLength = this.size();
            }
            for (int i = 0; i < shortLength; i++) {
                if (other.peek(i).compareTo(this.peek(i)) < 0) {
                    val = 1;
                    break;
                }
                if (other.peek(i).compareTo(this.peek(i)) > 0) {
                    val = -1;
                    break;
                }
                if (other.peek(i).compareTo(this.peek(i)) == 0 && (shortLength - 1) == i && (other.size() > this.size)) {
                    val = -1;
                    break;
                }
                if (other.peek(i).compareTo(this.peek(i)) == 0 && (shortLength - 1) == i && (other.size() < this.size)) {
                    val = 1;
                    break;
                }
            }
        }
        return val;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        } else {
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;
            // this if block was needed
            if (this.size() != other.size()) {
                return false;
            }
            for (int i = 0; i < other.size(); i++) {
                if (!(this.peek(i).equals(other.peek(i)))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size; i++) {
            hash = 31 * hash + peek(i).hashCode();
        }
        return hash;
    }
}