package datastructures.dictionaries;

import cse332.datastructures.trees.BinarySearchTree;

public class AVLTree<K extends Comparable<? super K>, V> extends BinarySearchTree<K, V>  {
    private static final int ALLOWED_IMBALANCE = 1;

    @Override
    public V insert(K key, V value) {
        V oldValue = super.find(key);
        this.root = insert(key, value, toAVL(this.root));
        return oldValue;
    }

    private AVLNode insert(K key, V value, AVLNode t) {
        if (t == null) {
            this.size++;
            return new AVLNode(key, value);
        }
        // compare key of node to insert and subtree root
        int compareResult = Integer.signum(key.compareTo(t.key));

        if (compareResult == 0) {
            t.value = value;
            return t;
        }

        if (compareResult < 0) {
            t.children[0] = insert(key, value, toAVL(t.children[0]));
        } else {
            t.children[1] = insert(key, value, toAVL(t.children[1]));
        }
        return balance(t);
    }

    private AVLNode balance (AVLNode t) {
        if (t == null) {
            return t;
        }
        int diff = height(toAVL(t.children[0])) - height(toAVL(t.children[1]));
        int imbalance = Math.abs(diff);
        if (imbalance > ALLOWED_IMBALANCE) {
            int child = (diff > 0) ? 0 : 1;
            if (height(toAVL(t.children[child].children[child])) < height(toAVL(t.children[child].children[1-child]))) {
                t.children[child] = rotateWithChild(1-child, toAVL(t.children[child]));
            }
            t = rotateWithChild(child, t);
        }
        t.height = Math.max(height(toAVL(t.children[0])), height(toAVL(t.children[1]))) + 1;
        return t;
    }

    //proje=c-> {   t[1976]-> {     e-> {       d[2302]     },     s[4465],     i-> {       n-> {         g[5133]       },       o-> {         n[8841]       },       l-> {         e[9674]       }     }   } } c=t[1976]-> {   e-> {     d[2302]   },   s[4465],   i-> {     n-> {       g[5133]     },     o-> {       n[8841]     },     l-> {       e[9674]     }   } } t=[1976]-> {  e-> {    d[2302]  },  s[4465],  i-> {    n-> {      g[5133]    },    o-> {      n[8841]    },    l-> {      e[9674]    }  } }

    private AVLNode rotateWithChild(int child, AVLNode k2) {
        AVLNode k1 = toAVL(k2.children[child]);
        k2.children[child] = k1.children[1-child];
        k1.children[1-child] = k2;
        k2.height = Math.max(height(toAVL(k2.children[child])), height(toAVL(k2.children[1-child]))) + 1;
        k1.height = Math.max(height(toAVL(k1.children[child])), k2.height) + 1;
        return k1;
    }

    protected class AVLNode extends BSTNode {
        public int height;

        public AVLNode(K key, V value) {
            super(key, value);
            this.height = 0;
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height(AVLNode t) {
        return t == null ? -1 : t.height;
    }

    /**
     * toAVL casts BSTNode node to AVLNode
     */
    @SuppressWarnings("unchecked")
    private AVLNode toAVL(BinarySearchTree.BSTNode node) {
        if (node instanceof AVLTree.AVLNode) {
            return (AVLNode) node;
        }
        return null;
    }
}
