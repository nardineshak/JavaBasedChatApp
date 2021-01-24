package datastructures.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;
import datastructures.worklists.ArrayStack;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }


    @Override
    public V insert(K key, V value) {
        if(key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if(key.toString().equals("")){
            this.root.value = value;
            this.size++;
            return null;
        }
        Iterator<A> keyItr = key.iterator();
        HashTrieNode currentNode = (HashTrieNode) this.root;
        A currentCharacter;
        while(keyItr.hasNext()){
            currentCharacter = keyItr.next();
            if(currentNode.pointers.containsKey(currentCharacter)){
                if(keyItr.hasNext() == false){
                    V removedValue = currentNode.pointers.get(currentCharacter).value;
                    currentNode.pointers.get(currentCharacter).value = value;
                    return removedValue;
                }
            }else{
                if(keyItr.hasNext()){
                    currentNode.pointers.put(currentCharacter, new HashTrieNode());
                }else{
                    currentNode.pointers.put(currentCharacter, new HashTrieNode(value));
                }
            }
            currentNode = currentNode.pointers.get(currentCharacter);
        }
        this.size++;
        return null;
    }

    @Override
    public V find(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        if(key.toString().equals("")){
            return this.root.value;
        }
        A currentCharacter;
        HashTrieNode current = (HashTrieNode) this.root;
        Iterator<A> keyItr = key.iterator();
        V desiredVal = null;
        while(keyItr.hasNext()){
            currentCharacter = keyItr.next();
            if(current.pointers.containsKey(currentCharacter)){
                if(keyItr.hasNext() == false){
                    desiredVal = current.pointers.get(currentCharacter).value;
                }
                current = current.pointers.get(currentCharacter);
            }else{
                desiredVal = null;
                break;
            }
        }
        return desiredVal;
    }

    @Override
    public boolean findPrefix(K key) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(key.toString().equals("")){
            return true;
        }
        Iterator<A> keyItr = key.iterator();
        HashTrieNode current = (HashTrieNode) this.root;
        A currentCharacter;
        boolean prefixPresent = false;
        while(keyItr.hasNext()){
            currentCharacter = keyItr.next();
            if(current.pointers.containsKey(currentCharacter)){
                prefixPresent = true;
            }else{
                prefixPresent = false;
                break;
            }
            current = current.pointers.get(currentCharacter);
        }
        return prefixPresent;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Iterator<A> keyItr = key.iterator();
        HashTrieNode current = (HashTrieNode) this.root;
        A currentCharacter;
        if(key.toString().equals("")){
            if(current.value != null){
                current.value = null;
                this.size--;
            }
            return;
        }else {
            //iterate down to the last character and adds each node except the last one to a stack while doing so
            ArrayStack<HashTrieNode> path = new ArrayStack<>();
            ArrayStack<A> backWardCharacters = new ArrayStack<>();
            path.add((HashTrieNode) this.root);
            while (keyItr.hasNext()) {
                currentCharacter = keyItr.next();
                if (current.pointers.containsKey(currentCharacter)) {
                    if(keyItr.hasNext() == false){
                        current = current.pointers.get(currentCharacter);
                        backWardCharacters.add(currentCharacter);
                    }else{
                        current = current.pointers.get(currentCharacter);
                        path.add(current);
                        backWardCharacters.add(currentCharacter);
                    }
                } else {
                    return;
                }
            }
            //iterate up to the root, delete if the node doesn't have any children or a null value
            //and break out of while loop if the node does
            HashTrieNode parent;
            boolean lastElement = true;
            while(path.hasWork()){
                parent = path.next();
                currentCharacter = backWardCharacters.next();
                if(parent.pointers.get(currentCharacter).pointers.isEmpty() && lastElement == true){
                    parent.pointers.remove(currentCharacter);
                    lastElement = false;
                }else if(!(parent.pointers.get(currentCharacter).pointers.isEmpty()) && lastElement == true){
                    parent.pointers.get(currentCharacter).value = null;
                    lastElement = false;
                    return;
                } else if((parent.pointers.get(currentCharacter).value == null
                        && parent.pointers.get(currentCharacter).pointers.isEmpty())){
                    parent.pointers.remove(currentCharacter);
                    lastElement = false;
                }else{
                    break;
                }
            }
            this.size--;
        }
    }

    @Override
    public void clear() {
        this.root = new HashTrieNode();
        this.size = 0;
    }
}
