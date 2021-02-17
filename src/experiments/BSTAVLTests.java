package experiments;

import cse332.datastructures.trees.BinarySearchTree;
import datastructures.dictionaries.AVLTree;

import java.io.FileNotFoundException;

public class BSTAVLTests {

    public static void main(String[] args) throws FileNotFoundException {
        int[] intElements = {1000, 2000, 4000, 8000, 16000, 32000};
//
//        for (int amount : intElements) {
//            timer(() -> insertAVLRandom(amount));
//        }

        int[] lottaElts = {500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000, 10500, 11000, 11500, 12000, 12500, 13000, 13500, 14000, 14500, 15000, 15500, 16000, 16500, 17000, 17500, 18000, 18500, 19000, 19500, 20000};

        for (int amount : lottaElts) {
            findBSTRandom(amount);

        }


    }

    public static void timer(Runnable test) {
        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;
        double totalTime = 0;

        for (int i = 0; i < NUM_TESTS; i++) {
            long startTime = System.currentTimeMillis();
            test.run();
            long endTime = System.currentTimeMillis();
            if (NUM_WARMUP <= i) {
                totalTime += (endTime - startTime);
            }
            System.out.println("bst insert test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void insertBSTWorst(int numElements, BinarySearchTree<Integer, Integer> tree) {
        for (int j = 1; j < numElements; j++) {
            tree.insert(j, j);
        }
    }

    public static void insertBSTBest(int numElements) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(numElements / 2, 1);
        for (int j = 1; j < numElements; j++) {
            bst.insert(j, j);
        }
    }

    public static void insertBSTRandom(int numElements) {
        int randomNum = (int) (Math.random() * numElements);
        System.out.println("random number generated is: " + randomNum);

        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(randomNum, 1);
        for (int j = 1; j < numElements; j++) {
            bst.insert(j, j);
        }
    }

    public static void insertAVLWorst(int numElements) {
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        avl.insert(numElements / 2, 1);
        for (int j = 1; j < numElements; j++) {
            avl.insert(j, j);
        }
    }

    public static void insertAVLBest(int numElements) {
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (int j = 1; j < numElements; j++) {
            avl.insert(j, j);
        }
    }

    public static void insertAVLRandom(int numElements) {
        int randomNum = (int) (Math.random() * numElements);
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        avl.insert(randomNum, 1);
        for (int j = 1; j < numElements; j++) {
            avl.insert(j, j);
        }
    }

    public static void findBSTKeyBest(int numElements) {
        long totalTime2 = 0;

        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(numElements / 2, 1);
        for (int j = 1; j < numElements; j++) {
            bst.insert(j, j);
        }

        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;
        for (int i = 1; i < NUM_TESTS; i++) {
            //
            for (int j = 1; j < numElements; j++) {
                long startTime = System.currentTimeMillis();
                bst.find(j);
                long endTime = System.currentTimeMillis();
                totalTime2 += (endTime - startTime);
            }
            //
        }
        double averageRuntime = (double) totalTime2;
        System.out.println("average bst find per element = " + averageRuntime + "ms");
    }

    public static void findBSTRandom(int numElements) {
        long totalTime2 = 0;
        int randomRoot = (int) (Math.random() * numElements);

        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(randomRoot, 1);
        for (int j = 1; j < numElements; j++) {
            bst.insert(j, j);
        }

        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;
        for (int i = 1; i < NUM_TESTS; i++) {
            //
            for (int j = 1; j < numElements; j++) {
                int randomNum = (int) (Math.random() * numElements);
                long startTime = System.currentTimeMillis();
                bst.find(randomNum);
                long endTime = System.currentTimeMillis();
                totalTime2 += (endTime - startTime);
                if (NUM_WARMUP <= i) {
                    totalTime2 += (endTime - startTime);
                }
            }
        }
        double averageRuntime = (double) totalTime2 / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average bst find per element = " + averageRuntime + "ms");
    }

    public static void findBSTKeyWorst(int numElements) {

        double totalTime = 0;
        long totalTime2 = 0;

        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(1, 1);
        for (int j = 1; j < numElements; j++) {
            bst.insert(j, j);
        }

        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;
        for (int i = 1; i < NUM_TESTS; i++) {
            //
            for (int j = 1; j < numElements; j++) {
                long startTime = System.currentTimeMillis();
                bst.find(j);
                long endTime = System.currentTimeMillis();
                totalTime2 += (endTime - startTime);
            }
            //
        }
        double averageRuntime = (double) totalTime2;
        System.out.println("average bst find per element = " + averageRuntime + "ms");
    }

    public static void findAVLKey(int numElements) {
        double totalTime = 0;
        long totalTime2 = 0;

        AVLTree<Integer, Integer> avl = new AVLTree<>();
        avl.insert(1, 1);
        for (int j = 1; j < numElements; j++) {
            avl.insert(j, j);
        }

        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;
        for (int i = 1; i < NUM_TESTS; i++) {
            //
            for (int j = 1; j < numElements; j++) {
                long startTime = System.currentTimeMillis();
                avl.find(j);
                long endTime = System.currentTimeMillis();
                totalTime2 += (endTime - startTime);
            }
            //
        }
        double averageRuntime = (double) totalTime2;
        System.out.println("average avl find per element = " + averageRuntime + "ms");
    }
}

