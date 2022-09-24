package AVLTree;

public class AVLTreeMy<E extends Comparable<E>> {

    private class Node{
        public E val;
        public Node left;
        public Node right;

        public Node(E val){
            this.val = val;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;


}
