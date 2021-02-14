package experiments;

import cse332.datastructures.containers.Item;
import cse332.datastructures.trees.BinarySearchTree;
import cse332.datastructures.trees.BinarySearchTree.BSTNode;
import cse332.interfaces.misc.Dictionary;
import datastructures.dictionaries.AVLTree;

public class BSTAVLTests {
    public static void main(String[] args) {

        int NUM_TESTS = 6;
        int NUM_WARMUP = 3;

        double totalTime = 0;
            for (int i = 0; i < NUM_TESTS; i++) {
                long startTime = System.currentTimeMillis();

                // code here

                long endTime = System.currentTimeMillis();
                if (NUM_WARMUP <= i) {
                    totalTime += (endTime - startTime);
                }
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
    }
}
