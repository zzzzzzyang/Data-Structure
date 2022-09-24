package AVLTree;

import java.util.ArrayList;
import java.util.Random;

public class AVLTreeMy<E extends Comparable<E>> {

    private class Node{
        public E val;
        public Node left;
        public Node right;
        public int height;

        public Node(E val){
            this.val = val;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTreeMy(){
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getHeight(Node root){
        return root == null ? 0 : root.height;
    }

    public int getBalanceFactor(Node root){
        if (root == null) return 0;
        return getHeight(root.left) - getHeight(root.right);
    }

    // 判断该二叉树是否是一颗二分搜索树
    public boolean isBST(){
        ArrayList<E> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i-1).compareTo(keys.get(i)) > 0)
                return false;
        }
        return true;
    }

    private void inOrder(Node root, ArrayList<E> keys){
        if (root != null){
            inOrder(root.left, keys);
            keys.add(root.val);
            inOrder(root.right, keys);
        }
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node root){
        if (root == null)
            return true;
        boolean left = isBalanced(root.left);
        boolean right = isBalanced(root.right);

        if (left && right){
            return Math.abs(getBalanceFactor(root)) < 2;
        }
        return false;
    }

    // LL 右旋转
    private Node rightRotate(Node root){
        Node newRoot = root.left;
        Node right = newRoot.right;
        newRoot.right = root;
        root.left = right;

        // 更新高度
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    // RR 左旋转
    private Node leftRotate(Node root){
        Node newRoot = root.right;
        Node left = newRoot.left;
        newRoot.left = root;
        root.right = left;

        // 更新高度
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    public void add(E e){
        root = add(root, e);
    }

    private Node add(Node root, E e){
        if (root == null){
            size ++;
            return new Node(e);
        }
        if (e.compareTo(root.val) < 0)
            root.left = add(root.left, e);
        else if(e.compareTo(root.val) > 0)
            root.right = add(root.right, e);

        // 更新height
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        // 平衡调整
        int BF = getBalanceFactor(root);
        int leftBF = getBalanceFactor(root.left);
        int rightBF = getBalanceFactor(root.right);
        if (BF > 1 && leftBF >= 0) { // LL
            return rightRotate(root);
        } else if (BF < -1 && rightBF <= 0) // RR
            return leftRotate(root);
        else if (BF > 1 && leftBF < 0){ // LR
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }else if (BF < -1 && rightBF > 0){ // RL
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }



    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node root, E e){
        if (root == null)
            return null;
        Node ret;
        if (e.compareTo(root.val) < 0){
            root.left = remove(root.left, e);
            ret = root;
        } else if (e.compareTo(root.val) > 0){
            root.right = remove(root.right, e);
            ret = root;
        } else {
            if (root.left == null){
                Node right = root.right;
                root.right = null;
                size --;
                ret = right;
            }else if (root.right == null){
                Node left = root.left;
                root.left = null;
                size --;
                ret = left;
            }else{
                Node min = minimum(root.right);
//                min.right = removeMin(root.right);
                min.right = remove(root.right, min.val);
                min.left = root.left;
                root.left = null;
                root.right = null;
                ret = min;
            }
        }

        if (ret == null)
            return null;

        // 更新height
        ret.height = 1 + Math.max(getHeight(ret.left), getHeight(ret.right));

        // 平衡调整
        int BF = getBalanceFactor(ret);
        int leftBF = getBalanceFactor(ret.left);
        int rightBF = getBalanceFactor(ret.right);
        if (BF > 1 && leftBF >= 0) { // LL
            return rightRotate(ret);
        } else if (BF < -1 && rightBF <= 0) // RR
            return leftRotate(ret);
        else if (BF > 1 && leftBF < 0){ // LR
            ret.left = leftRotate(ret.left);
            return rightRotate(ret);
        }else if (BF < -1 && rightBF > 0){ // RL
            ret.right = rightRotate(ret.right);
            return leftRotate(ret);
        }

        return ret;
    }

    private Node minimum(Node root){
        if (root == null)
            return null;
        if (root.left != null)
            return minimum(root.left);
        return root;
    }

    private Node removeMin(Node root){
        if (root == null)
            return null;
        if (root.left != null)
            root.left = removeMin(root.left);
        else{
            Node right = root.right;
            root.right = null;
            size --;
            return right;
        }
        return root;
    }

    public static void main(String[] args) {
        AVLTreeMy<Integer> avlTree = new AVLTreeMy<Integer>();
        Random random = new Random();
        int n = 10000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(100000));
        }

        for (int i = 0; i < n; i++) {
            int num = list.get(i);
            avlTree.add(num);
        }

        System.out.println(avlTree.getSize());

        for (int num : list){
            avlTree.remove(num);
            if (!avlTree.isBalanced())
                throw new RuntimeException("Error!");
        }
        System.out.println(avlTree.isBST());
        System.out.println(avlTree.isBalanced());
    }
}
