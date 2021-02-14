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

                BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
                for (int j = 1; j < Integer.MAX_VALUE; j++) {
                    bst.insert(i, i);
                }

                long endTime = System.currentTimeMillis();
                if (NUM_WARMUP <= i) {
                    totalTime += (endTime - startTime);
                }
                System.out.println("test " + (i + 1) + " = " + (endTime - startTime) + "ms");
        }
        double averageRuntime = totalTime / (NUM_TESTS - NUM_WARMUP);
        System.out.println("average runtime = " + averageRuntime + "ms");
    }
}
