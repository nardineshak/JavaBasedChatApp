package experiments;

import cse332.datastructures.containers.Item;
import cse332.datastructures.trees.BinarySearchTree;
import cse332.interfaces.misc.BString;
import cse332.types.AlphabeticString;
import datastructures.dictionaries.AVLTree;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import datastructures.dictionaries.MoveToFrontList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class GeneralPurposeDictionaryTest {

    public static void main(String[] args) throws FileNotFoundException {
        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;
        Scanner input = new Scanner(new File("alice.txt"));
        System.out.println("CHT Insert Test");
        insertHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        insertHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        insertHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        insertHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        insertHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        System.out.println();
        System.out.println("CHT Find Test");
        System.out.println("");
        findHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        findHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        findHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        findHTM(NUM_TESTS, NUM_WARMUP);
        System.out.println();
        findHTM(NUM_TESTS, NUM_WARMUP);
    }

    public static void insertBST(int NUM_TESTS, int NUM_WARMUP) throws FileNotFoundException{
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        double totalTime = 0;
        int counter = 1;
        for (int i = 0; i < NUM_TESTS; i++) {
            Scanner input = new Scanner(new File("alice.txt"));
            long startTime = System.currentTimeMillis();

            while(input.hasNext()){
                bst.insert(input.next(), counter);
                counter++;
            }
            long endTime = System.currentTimeMillis();

            if(NUM_WARMUP <= i){
                totalTime += (endTime - startTime);
            }
            System.out.println("bst insert test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void findBST(int NUM_TESTS, int NUM_WARMUP) throws FileNotFoundException{
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        Scanner input = new Scanner(new File("alice.txt"));
        int counter = 1;
        double totalTime = 0;
        while(input.hasNext()){
            bst.insert(input.next(), counter);
            counter++;
        }
        for (int i = 0; i < NUM_TESTS; i++) {
            Iterator<Item<String,Integer>> itr= bst.iterator();
            long startTime = System.currentTimeMillis();
            while (itr.hasNext()){
                bst.find(itr.next().key);
            }
            long endTime = System.currentTimeMillis();
            if(NUM_WARMUP <= i){
                totalTime += (endTime - startTime);
            }
            System.out.println("bst find test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void insertAVL(int NUM_TESTS, int NUM_WARMUP) throws FileNotFoundException{
        AVLTree<String, Integer> bst = new AVLTree<>();
        double totalTime = 0;
        int counter = 1;
        for (int i = 0; i < NUM_TESTS; i++) {
            Scanner input = new Scanner(new File("alice.txt"));
            long startTime = System.currentTimeMillis();

            while(input.hasNext()){
                bst.insert(input.next(), counter);
                counter++;
            }
            long endTime = System.currentTimeMillis();

            if(NUM_WARMUP <= i){
                totalTime += (endTime - startTime);
            }
            System.out.println("avl insert test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void findAVL(int NUM_TESTS, int NUM_WARMUP) throws FileNotFoundException{
        AVLTree<String, Integer> avl = new AVLTree<>();
        Scanner input = new Scanner(new File("alice.txt"));
        int counter = 1;
        double totalTime = 0;
        while(input.hasNext()){
            avl.insert(input.next(), counter);
            counter++;
        }
        for (int i = 0; i < NUM_TESTS; i++) {
            Iterator<Item<String,Integer>> itr= avl.iterator();
            long startTime = System.currentTimeMillis();
            while (itr.hasNext()){
                avl.find(itr.next().key);
            }
            long endTime = System.currentTimeMillis();
            if(NUM_WARMUP <= i){
                totalTime += (endTime - startTime);
            }
            System.out.println("avl find test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void insertCHT(int NUM_TESTS, int NUM_WARMUP) throws FileNotFoundException{
        ChainingHashTable<String, Integer> bst = new ChainingHashTable<>(MoveToFrontList::new);
        double totalTime = 0;
        int counter = 1;
        for (int i = 0; i < NUM_TESTS; i++) {
            Scanner input = new Scanner(new File("alice.txt"));
            long startTime = System.currentTimeMillis();

            while(input.hasNext()){
                bst.insert(input.next(), counter);
                counter++;
            }
            long endTime = System.currentTimeMillis();

            if(NUM_WARMUP <= i){
                totalTime += (endTime - startTime);
            }
            System.out.println("cht insert test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void findCHT(int NUM_TESTS, int NUM_WARMUP) throws FileNotFoundException{
        ChainingHashTable<String, Integer> cht = new ChainingHashTable<>(MoveToFrontList::new);
        Scanner input = new Scanner(new File("alice.txt"));
        int counter = 1;
        double totalTime = 0;
        while(input.hasNext()){
            cht.insert(input.next(), counter);
            counter++;
        }
        for (int i = 0; i < NUM_TESTS; i++) {
            Iterator<Item<String,Integer>> itr= cht.iterator();
            long startTime = System.currentTimeMillis();
            while (itr.hasNext()){
                cht.find(itr.next().key);
            }
            long endTime = System.currentTimeMillis();
            if(NUM_WARMUP <= i){
                totalTime += (endTime - startTime);
            }
            System.out.println("cht find test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void insertHTM(int NUM_TESTS, int NUM_WARMUP) throws FileNotFoundException{
        HashTrieMap<Character, AlphabeticString, Integer> htm = new HashTrieMap<>(AlphabeticString.class);
        double totalTime = 0;
        int counter = 1;
        for (int i = 0; i < NUM_TESTS; i++) {
            Scanner input = new Scanner(new File("alice.txt"));
            long startTime = System.currentTimeMillis();

            while(input.hasNext()){
                htm.insert(new AlphabeticString(input.next()), counter);
                counter++;
            }
            long endTime = System.currentTimeMillis();

            if(NUM_WARMUP <= i){
                totalTime += (endTime - startTime);
            }
            System.out.println("htm insert test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void findHTM(int NUM_TESTS, int NUM_WARMUP) throws FileNotFoundException{
        HashTrieMap<Character, AlphabeticString, Integer> htm = new HashTrieMap<>(AlphabeticString.class);
        Scanner input = new Scanner(new File("alice.txt"));
        int counter = 1;
        double totalTime = 0;
        while(input.hasNext()){
            htm.insert(new AlphabeticString(input.next()), counter);
            counter++;
        }
        for (int i = 0; i < NUM_TESTS; i++) {
            Iterator<Item<AlphabeticString,Integer>> itr= htm.iterator();
            long startTime = System.currentTimeMillis();
            while (itr.hasNext()){
                htm.find(itr.next().key);
            }
            long endTime = System.currentTimeMillis();
            if(NUM_WARMUP <= i){
                totalTime += (endTime - startTime);
            }
            System.out.println("htm find test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }


}
