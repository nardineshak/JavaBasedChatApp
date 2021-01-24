package datastructures.worklists;

import cse332.interfaces.worklists.FIFOWorkList;
import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {

    private final Node<E> head;
    private final Node<E> tail;
    private int numElements = 0;
    
    public ListFIFOQueue() {
        //creating an empty ListFIFOQueue with references
        //to the head and tail of our list
        head = new Node<>();
        tail = new Node<>();
        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;
    }

    @Override
    public void add(E work) {
            Node<E> newNode = new Node<>(work);
            newNode.next = head.next;
            newNode.next.prev = newNode;
            newNode.prev = head;
            head.next = newNode;
            numElements++;
    }

    @Override
    public E peek() {
        if(this.hasWork()){
            return tail.prev.data;
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public E next() {
        if(this.hasWork()){
            Node<E> removedWork = tail.prev;
            tail.prev = removedWork.prev;
            tail.prev.next = tail;
            numElements--;
            return removedWork.data;
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        numElements = 0;
    }

    private static class Node<E>{

        //Node data fields
        public E data;
        public Node<E> prev;
        public Node<E> next;

        //Default Node constructor
        public Node(){
        }

        //Parameterized Node constructor
        public Node(E data){
            this.data = data;
        }

    }
}
