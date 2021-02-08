package datastructures.dictionaries;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.misc.SimpleIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. You must implement a generic chaining hashtable. You may not
 * restrict the size of the input domain (i.e., it must accept
 * any key) or the number of inputs (i.e., it must grow as necessary).
 * 3. Your HashTable should rehash as appropriate (use load factor as
 * shown in class!).
 * 5. HashTable should be able to resize its capacity to prime numbers for more
 * than 200,000 elements. After more than 200,000 elements, it should
 * continue to resize using some other mechanism.
 * 6. We suggest you hard code some prime numbers. You can use this
 * list: http://primes.utm.edu/lists/small/100000.txt
 * NOTE: Do NOT copy the whole list!
 * 7. When implementing your iterator, you should NOT copy every item to another
 * dictionary/list and return that dictionary/list's iterator.
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private Supplier<Dictionary<K, V>> newChain;
    private Dictionary<K, V>[] hashTable;
    private int capacity;
    private final double lambda = 0.9;
    private final int primeNums[] = {11, 23, 47, 97, 193, 389, 787, 1559, 3119, 6247, 12473, 24943, 49891, 99787, 199967};
    private int primeIndex = 0;

    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        this.newChain = newChain;
        capacity = primeNums[primeIndex];
        hashTable = (Dictionary<K, V>[]) new Dictionary[capacity];
        primeIndex++;
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        //check lamda/grow array
        //if less than the size 200,000 use the prime number method
        //if greater than 200,000 just double
        double currentLamda = (1.0) * size / capacity;
        if (currentLamda >= lambda && primeIndex < primeNums.length) {
            capacity = primeNums[primeIndex];
            hashTable = biggerCapacity(hashTable, capacity);
            primeIndex++;
        }
        if (currentLamda >= lambda && primeIndex >= primeNums.length) {
            capacity *= 2;
            hashTable = biggerCapacity(hashTable, capacity);
        }
        //check whether key already exists, if it does just update value
        //if it doesn't add the new value
        int index = Math.abs(key.hashCode());
        index %= capacity;
        if (hashTable[index] == null) {
            hashTable[index] = newChain.get();
            hashTable[index].insert(key, value);
            size++;
        } else {
            if (hashTable[index].find(key) != null) {
                V oldValue = find(key);
                hashTable[index].insert(key, value);
                return oldValue;
            } else {
                hashTable[index].insert(key, value);
                size++;
            }
        }
        return null;
    }

    public Dictionary<K, V>[] biggerCapacity(Dictionary<K, V>[] orgHT, int desiredSize) {
        Dictionary<K, V>[] temp = (Dictionary<K, V>[]) new Dictionary[desiredSize];
        for (int i = 0; i < orgHT.length; i++) {
            if (orgHT[i] != null) {
                Iterator<Item<K, V>> itr = orgHT[i].iterator();
                int index;
                while (itr.hasNext()) {
                    Item<K, V> element = itr.next();
                    index = Math.abs(element.key.hashCode());
                    index %= desiredSize;
                    if (temp[index] == null) {
                        temp[index] = newChain.get();
                        temp[index].insert(element.key, element.value);
                    } else {
                        temp[index].insert(element.key, element.value);
                    }
                }
            }
        }
        return temp;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int index = Math.abs(key.hashCode());
        index %= capacity;
        if (this.size == 0 || hashTable[index] == null) {
            return null;
        }
        return hashTable[index].find(key);
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new HASHTABLEIterator();
    }

    private class HASHTABLEIterator extends SimpleIterator<Item<K, V>> {

        private int currentIndex;
        private int elementCount;
        private Iterator<Item<K, V>> currentItr;

        public HASHTABLEIterator() {
            currentIndex = 0;
            elementCount = 0;
            currentItr = hashTable[currentIndex].iterator();
        }

        @Override
        public Item<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                if (currentItr.hasNext()) {
                    elementCount++;
                    return currentItr.next();
                } else {
                    elementCount++;
                    currentIndex++;
                    if (currentIndex < hashTable.length && hashTable[currentIndex] == null) {
                        currentIndex++;
                        while (currentIndex < hashTable.length && hashTable[currentIndex] == null) {
                            currentIndex++;
                        }
                    }
                    if (currentIndex >= hashTable.length) {
                        return null;
                    }
                    currentItr = hashTable[currentIndex].iterator();
                    return currentItr.next();
                }
            }
        }

        @Override
        public boolean hasNext() {
            boolean keepGoingIndex = true;
            if ((currentIndex < hashTable.length && elementCount >= size) || (currentIndex >= hashTable.length)) {
                keepGoingIndex = false;
            }
            return (currentItr.hasNext() || keepGoingIndex);
        }
    }
}
