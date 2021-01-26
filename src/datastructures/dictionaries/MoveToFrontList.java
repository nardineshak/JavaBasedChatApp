package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.DeletelessDictionary;
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

    private int size = 0;
    private Node<K, V> front;

    @Override
    public V insert(K key, V value) {
        if(this.size == 0){
            front = new Node<>(key, value);
        }else{
            if(this.find(key) == null){
                Node<K, V> newNode = new Node<>(key, value);
                newNode.next = front;
                front = newNode;
            }else{
                V oldVal = front.data;
                front.data = value;
                return oldVal;
            }
        }
        size++;
        return value;
    }

    @Override
    public V find(K key) {
        if(size == 0){
            return null;
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
                foundVal = front.data;
                found = true;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        return foundVal;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        throw new NotYetImplementedException();
    }

    private static class Node<K, V>{

        //Node data fields
        public K key;
        public V data;
        public MoveToFrontList.Node<K, V> next;

        //Default Node constructor
        public Node(){
        }

        //Parameterized Node constructor
        public Node(K key, V data){
            this.key = key;
            this.data = data;
        }

    }
}
