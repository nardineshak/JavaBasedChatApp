package experiments;


import cse332.datastructures.trees.BinarySearchTree;
import datastructures.dictionaries.AVLTree;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.MoveToFrontList;

public class ChainingHashTableTest {
    public static void main(String[] args) {

        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;
        int elementCount = 100000;

//        System.out.printf("BST");
//        findTwiceBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        findTwiceBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        findTwiceBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println("Final Twice MTFL Test (Trial 1)");
//        System.out.println("MTFL");
//        findTwiceMTFL(NUM_TESTS, NUM_WARMUP, elementCount);
//        findTwiceMTFL(NUM_TESTS, NUM_WARMUP, elementCount);
//        findTwiceMTFL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println("AVLTree");
//        findTwiceAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        findTwiceAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        findTwiceAVL(NUM_TESTS, NUM_WARMUP, elementCount);


//        System.out.println("Final Twice BST Test (Trial 1)");
//        findTwiceBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println("Final Twice BST Test (Trial 2)");
//        findTwiceBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println("Final Twice BST Test (Trial 3)");
//        findTwiceBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//
//        System.out.println("Final Twice AVL Test (Trial 1)");
//        findTwiceAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println("Final Twice AVL Test (Trial 1)");
//        findTwiceAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println("Final Twice AVL Test (Trial 1)");
//        findTwiceAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();

//        System.out.println("Insert AVL Test (Trial 1)");
//        insertAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println("Insert AVL Test (Trial 2)");
//        insertAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println("Insert AVL Test (Trial 3)");
//        insertAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println();
//        System.out.println("Find AVL Test (Trial 1)");
//        findAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println("Find AVL Test (Trial 2)");
//        findAVL(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println("Find AVL Test (Trial 3)");
//        findAVL(NUM_TESTS, NUM_WARMUP, elementCount);

//        System.out.println("Insert BST Test (Trial 1)");
//        insertBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println("Insert BST Test (Trial 2)");
//        insertBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println("Insert BST Test (Trial 3)");
//        insertBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println();
//        System.out.println();
//        System.out.println("Find BST Test (Trial 1)");
//        findBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println("Find BST Test (Trial 2)");
//        findBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        System.out.println("Find BST Test (Trial 3)");
//        findBST(NUM_TESTS, NUM_WARMUP, elementCount);
//        findBST(NUM_TESTS, NUM_WARMUP, elementCount);



        findMTFL(NUM_TESTS, NUM_WARMUP, elementCount);

    }

    public static void insertMTFL(int numTests, int numWarmUp, int elementCount) {
        double totalTime = 0;
        for (int i = 0; i < numTests; i++) {
            long startTime = System.currentTimeMillis();

            ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(MoveToFrontList::new);
            for (int j = 0; j < elementCount; j++) {
                hashTable.insert(j, j);
            }

            long endTime = System.currentTimeMillis();
            if (numWarmUp <= i) {
                totalTime += (endTime - startTime);
            }
            System.out.println("test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }

        double averageRuntime = totalTime / (numTests - numWarmUp);
        System.out.printf("average runtime = " + averageRuntime + "ms");
    }


    public static void insertBST(int numTests, int numWarmUp, int elementCount) {
        double totalTime = 0;
        for (int i = 0; i < numTests; i++) {
            long startTime = System.currentTimeMillis();

            ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(BinarySearchTree::new);
            hashTable.insert(elementCount / 2, elementCount / 2);
            for (int j = 1; j < elementCount; j++) {
                hashTable.insert(j,j);
            }

            long endTime = System.currentTimeMillis();
            if (numWarmUp <= i) {
                totalTime += (endTime - startTime);
            }
            System.out.println("test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }

        double averageRuntime = totalTime / (numTests - numWarmUp);
        System.out.printf("average runtime = " + averageRuntime + "ms");

    }


    public static void insertAVL(int numTests, int numWarmUp, int elementCount) {
        double totalTime = 0;
        for (int i = 0; i < numTests; i++) {
            long startTime = System.currentTimeMillis();

            ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(AVLTree::new);
            for (int j = 0; j < elementCount; j++) {
                hashTable.insert(j, j);
            }

            long endTime = System.currentTimeMillis();
            if (numWarmUp <= i) {
                totalTime += (endTime - startTime);
            }
            System.out.println("test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }

        double averageRuntime = totalTime / (numTests - numWarmUp);
        System.out.printf("average runtime = " + averageRuntime + "ms");

    }

    public static void findMTFL(int numTests, int numWarmUp, int elementCount) {
        double totalTime = 0;
        double totalTime2 = 0;
        for (int i = 0; i < numTests; i++) {
            ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(MoveToFrontList::new);
            for (int j = 1; j < elementCount; j++) {
                hashTable.insert(j, j);
            }
            long startTime = System.currentTimeMillis();

            for (int j = 1; j < elementCount; j++) {
                hashTable.find(j);
            }

            long endTime = System.currentTimeMillis();
            if (numWarmUp <= i) {
                totalTime += (endTime - startTime);
            }
        }

        double averageRuntime = totalTime / (elementCount);
        System.out.printf("average runtime = " + averageRuntime + "ms");
    }

    public static void findBST(int numTests, int numWarmUp, int elementCount) {
        double totalTime = 0;
        for (int i = 0; i < numTests; i++) {
            ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(BinarySearchTree::new);
            hashTable.insert(elementCount / 2, elementCount / 2);
            for (int j = 1; j < elementCount; j++) {
                hashTable.insert(j, j);
            }
            long startTime = System.currentTimeMillis();

            for (int j = 1; j < elementCount; j++) {
                hashTable.find(j);
            }

            long endTime = System.currentTimeMillis();
            if (numWarmUp <= i) {
                totalTime += (endTime - startTime);
            }
        }
        double averageRuntime = totalTime / (numTests - numWarmUp);
        System.out.printf("average runtime = " + averageRuntime + "ms");

    }


    public static void findAVL(int numTests, int numWarmUp, int elementCount) {
        double totalTime = 0;
        for (int i = 0; i < numTests; i++) {
            ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(AVLTree::new);
            for (int j = 1; j < elementCount; j++) {
                hashTable.insert(j, j);
            }
            long startTime = System.currentTimeMillis();

            for (int j = 1; j < elementCount; j++) {
                hashTable.find(j);
            }

            long endTime = System.currentTimeMillis();
            if (numWarmUp <= i) {
                totalTime += (endTime - startTime);
            }
            System.out.println("test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }

        double averageRuntime = totalTime / (numTests - numWarmUp);
        System.out.printf("average runtime = " + averageRuntime + "ms");
    }


    public static void findTwiceMTFL(int numTests, int numWarmUp, int elementCount) {
        double totalTime2 = 0;
        ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(MoveToFrontList::new);
        for (int j = 1; j < elementCount; j++) {
            hashTable.insert(j, j);
        }
        long startTime = 0;
        long endTime = 0;
        for (int j = 1; j < elementCount; j++) {
            startTime = System.currentTimeMillis();
            hashTable.find(j);
            hashTable.find(j);
            endTime = System.currentTimeMillis();
            totalTime2 += (endTime - startTime);
        }
        double averageRuntime = totalTime2 / elementCount;
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void findTwiceBST(int numTests, int numWarmUp, int elementCount) {
        double totalTime2 = 0;
        ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(BinarySearchTree::new);
        hashTable.insert(elementCount / 2, elementCount / 2);
        for (int j = 1; j < elementCount; j++) {
            hashTable.insert(j, j);
        }
        long startTime = 0;
        long endTime = 0;
        for (int j = 1; j < elementCount; j++) {
            startTime = System.currentTimeMillis();
            hashTable.find(j);
            hashTable.find(j);
            endTime = System.currentTimeMillis();
            totalTime2 += (endTime - startTime);
        }
        double averageRuntime = totalTime2 / elementCount;
        System.out.println("average runtime = " + averageRuntime + "ms");
    }

    public static void findTwiceAVL(int numTests, int numWarmUp, int elementCount) {
        double totalTime2 = 0;
        ChainingHashTable<Integer, Integer> hashTable = new ChainingHashTable(AVLTree::new);
        for (int j = 1; j < elementCount; j++) {
            hashTable.insert(j, j);
        }
        long startTime = 0;
        long endTime = 0;
        for (int j = 1; j < elementCount; j++) {
            startTime = System.currentTimeMillis();
            hashTable.find(j);
            hashTable.find(j);
            endTime = System.currentTimeMillis();
            totalTime2 += (endTime - startTime);
        }
        double averageRuntime = totalTime2 / elementCount;
        System.out.println("average runtime = " + averageRuntime + "ms");
    }


}
