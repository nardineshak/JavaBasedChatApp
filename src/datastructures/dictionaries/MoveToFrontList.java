package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cse332.datastructures.containers.Item;
import cse332.datastructures.trees.BinarySearchTree;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.SimpleIterator;
import cse332.interfaces.worklists.WorkList;
import datastructures.worklists.ArrayStack;
import datastructures.worklists.ListFIFOQueue;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. The list is typically not sorted.
 * 2. Add new items to the front of the list.
 * 3. Whenever find is called on an item, move it to the front of the 
 *    list. This means you remove the node from its current position 
 *    and make it the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 *    elements to the front.  The iterator should return elements in
 *    the order they are stored in the list, starting with the first
 *    element in the list. When implementing your iterator, you should 
 *    NOT copy every item to another dictionary/list and return that 
 *    dictionary/list's iterator. 
 */
public class MoveToFrontList<K, V> extends DeletelessDictionary<K, V> {

    private Node<K, V> front;

    @Override
    public V insert(K key, V value) {
        if(this.size() == 0){
            front = new Node<>(key, value);
            this.size++;
        }else{
            if(this.find(key) == null){
                Node<K, V> newNode = new Node<>(key, value);
                newNode.next = front;
                front = newNode;
                this.size++;
            }else{
                V oldVal = front.value;
                front.value = value;
                return oldVal;
            }
        }
        return value;
    }

    @Override
    public V find(K key) {
        if(this.size() == 0){
            return null;
        }
        if(front.key.equals(key)){
            return front.value;
        }
        Node<K, V> currentNode = front;
        Node<K,V> prevNode = null;
        boolean found = false;
        V foundVal = null;
        while(!found && currentNode != null){
            if(currentNode.key.equals(key)){
                if(prevNode != null){
                    prevNode.next = currentNode.next;
                }
                currentNode.next = front;
                front = currentNode;
                foundVal = front.value;
                found = true;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        return foundVal;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new MTFIterator();
    }

    private class MTFIterator extends SimpleIterator<Item<K, V>> {
        private MoveToFrontList.Node<K, V> current;

        public MTFIterator() {
            current = front;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public Item<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item<K, V> temp = current;
                current = current.next;
                return temp;
            }
        }
    }

    private static class Node<K, V> extends Item<K, V>{

        //Node data fields
        public MoveToFrontList.Node<K, V> next;

        //Parameterized Node constructor
        public Node(K key, V data){
            super(key, data);
        }

        public V getData() {
            return value;
        }

    }
}
