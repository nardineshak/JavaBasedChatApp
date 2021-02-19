package experiments;

import cse332.datastructures.containers.Item;
import cse332.types.AlphabeticString;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import datastructures.dictionaries.MoveToFrontList;
import datastructures.worklists.CircularArrayFIFOQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class HashCodeTests {


    public static void main(String[] args) throws FileNotFoundException {
        CircularArrayFIFOQueue.useBadHashCode = false;
        hashFind();
    }

//    public static void hashFind() throws FileNotFoundException {
//        ChainingHashTable<String, Integer> table = new ChainingHashTable<>(MoveToFrontList::new);
//        Scanner input = new Scanner(new File("alice.txt"));
//        int counter = 1;
//
//        long totalTime2 = 0;
//
//        while (input.hasNext()) {
//            table.insert(input.next(), counter);
//            counter++;
//
//
//        int NUM_TESTS = 6;
//        int NUM_WARMUP = 3;
//
//        for (int i = 0; i < NUM_TESTS; i++) {
//            Iterator<Item<String, Integer>> itr = table.iterator();
//            long startTime = System.currentTimeMillis();
//            while (itr.hasNext()) {
//                table.find(itr.next().key);
//            }
//            long endTime = System.currentTimeMillis();
//            totalTime2 += (endTime - startTime);
//
//            if (NUM_WARMUP <= i) {
//                totalTime2 += (endTime - startTime);
//            }
//        }
//        double averageRuntime = (double) totalTime2 / (NUM_TESTS - NUM_WARMUP);
//        System.out.println("hash find per total amount = " + averageRuntime + "ms");
//    }

    public static void hashFind() throws FileNotFoundException {
        ChainingHashTable<AlphabeticString, Integer> table = new ChainingHashTable<>(MoveToFrontList::new);
        String pathName = "kjv.txt";
        Scanner input = new Scanner(new File(pathName));
        int counter = 1;

        double totalTime = 0;

        while (input.hasNext()) {
            table.insert(new AlphabeticString(input.next()), counter);
            counter++;
        }
//        System.out.println(table.size());

        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;

        for (int i = 0; i < NUM_TESTS; i++) {
            input = new Scanner(new File(pathName));
            long startTime = System.currentTimeMillis();
            while (input.hasNext()) {
                // crash driven development
//                char[] array = new char[1];
//                System.out.println(array[1]);
                table.find(new AlphabeticString(input.next()));
            }
            long endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);

            if (NUM_WARMUP <= i) {
                totalTime += (endTime - startTime);
            }
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("hash find per total amount = " + averageRuntime + "ms");
    }

    public static void hashFind(int numElements) {
        ChainingHashTable<Integer, Integer> table = new ChainingHashTable<>(MoveToFrontList::new);
        long totalTime2 = 0;

        for (int j = 1; j < numElements; j++) {
            table.insert(j, j);
        }
        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;

        for (int i = 1; i < NUM_TESTS; i++) {

            //
            for (int j = 1; j < numElements; j++) {
                int randomNum = (int) (Math.random() * numElements);
                long startTime = System.currentTimeMillis();
                table.find(randomNum);
                long endTime = System.currentTimeMillis();
                totalTime2 += (endTime - startTime);

                if (NUM_WARMUP <= i) {
                    totalTime2 += (endTime - startTime);
                }
            }
        }
        double averageRuntime = (double) totalTime2 / (NUM_TESTS - NUM_WARMUP);
        System.out.println("hash find per total amount = " + averageRuntime + "ms");
    }

//    // original hashCode()
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        for (int i = 0; i < size; i++) {
//            hash = 31 * hash + peek(i).hashCode();
//        }
//        return hash;
//    }

//    // composite multiplier
//    @Override
//    public int hashCode() {
//    int hash = 0;
//        for (int i = 0; i < size; i++) {
//        hash = 2 * hash + peek(i).hashCode();
//    }
//        return hash;
//    }
//
//    // bad hash code that returns constant
//    @Override
//    public int hashCode() {
//        return 1;
//    }


}
