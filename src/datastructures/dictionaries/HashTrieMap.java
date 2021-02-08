package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;
import datastructures.worklists.ArrayStack;

import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<ChainingHashTable<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new ChainingHashTable<A, HashTrieNode>(MoveToFrontList::new);
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return new NODEIterator();
        }

        private class NODEIterator implements Iterator<Entry<A, HashTrieNode>> {
            Iterator<Item<A, HashTrieNode>> iterator;

            public NODEIterator() {
                iterator = pointers.iterator();
            }

            @Override
            public Entry<A, HashTrieNode> next() {
                Item<A, HashTrieNode> next = iterator.next();
                return new SimpleEntry<A, HashTrieNode>(next.key, next.value);
            }

            @Override
            public boolean hasNext() {
                return pointers != null;
            }
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }


    @SuppressWarnings("unchecked")
    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (key.toString().equals("")) {
            this.root.value = value;
            this.size++;
            return null;
        }
        Iterator<A> keyItr = key.iterator();
        HashTrieNode currentNode = (HashTrieNode) this.root;
        A currentCharacter;
        while (keyItr.hasNext()) {
            currentCharacter = keyItr.next();
            if (currentNode.pointers.find(currentCharacter) != null) {
                if (!keyItr.hasNext()) {
                    V removedValue = currentNode.pointers.find(currentCharacter).value;
                    currentNode.pointers.find(currentCharacter).value = value;
                    return removedValue;
                }
            } else {
                if (keyItr.hasNext()) {
                    currentNode.pointers.insert(currentCharacter, new HashTrieNode());
                } else {
                    currentNode.pointers.insert(currentCharacter, new HashTrieNode(value));
                }
            }
            currentNode = currentNode.pointers.find(currentCharacter);
        }
        this.size++;
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (key.toString().equals("")) {
            return this.root.value;
        }
        A currentCharacter;
        HashTrieNode current = (HashTrieNode) this.root;
        Iterator<A> keyItr = key.iterator();
        V desiredVal = null;
        while (keyItr.hasNext()) {
            currentCharacter = keyItr.next();
            if (current.pointers.find(currentCharacter) != null) {
                if (!keyItr.hasNext()) {
                    desiredVal = current.pointers.find(currentCharacter).value;
                }
                current = current.pointers.find(currentCharacter);
            } else {
                desiredVal = null;
                break;
            }
        }
        return desiredVal;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean findPrefix(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (key.toString().equals("")) {
            return true;
        }
        Iterator<A> keyItr = key.iterator();
        HashTrieNode current = (HashTrieNode) this.root;
        A currentCharacter;
        boolean prefixPresent = false;
        while (keyItr.hasNext()) {
            currentCharacter = keyItr.next();
            if (current.pointers.find(currentCharacter) != null) {
                prefixPresent = true;
            } else {
                prefixPresent = false;
                break;
            }
            current = current.pointers.find(currentCharacter);
        }
        return prefixPresent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Iterator<A> keyItr = key.iterator();
        HashTrieNode current = (HashTrieNode) this.root;
        A currentCharacter;
        if (key.toString().equals("")) {
            if (current.value != null) {
                current.value = null;
                this.size--;
            }
        } else {
            //iterate down to the last character and adds each node except the last one to a stack while doing so
            ArrayStack<HashTrieNode> path = new ArrayStack<>();
            ArrayStack<A> backWardCharacters = new ArrayStack<>();
            path.add((HashTrieNode) this.root);
            while (keyItr.hasNext()) {
                currentCharacter = keyItr.next();
                if (current.pointers.find(currentCharacter) != null) {
                    if (!keyItr.hasNext()) {
                        current = current.pointers.find(currentCharacter);
                        backWardCharacters.add(currentCharacter);
                    } else {
                        current = current.pointers.find(currentCharacter);
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
            while (path.hasWork()) {
                parent = path.next();
                currentCharacter = backWardCharacters.next();
                if (parent.pointers.find(currentCharacter).pointers.isEmpty() && lastElement) {
                    parent.pointers.delete(currentCharacter);
                    lastElement = false;
                } else if (!(parent.pointers.find(currentCharacter).pointers.isEmpty()) && lastElement) {
                    parent.pointers.find(currentCharacter).value = null;
                    lastElement = false;
                    return;
                } else if ((parent.pointers.find(currentCharacter).value == null
                        && parent.pointers.find(currentCharacter).pointers.isEmpty())) {
                    parent.pointers.delete(currentCharacter);
                    lastElement = false;
                } else {
                    break;
                }
            }
            this.size--;
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
