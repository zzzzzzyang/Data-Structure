package RedBlackTree;


import java.util.ArrayList;
import java.util.Random;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }
    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    // 判断该二叉树是否是一颗二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i-1).compareTo(keys.get(i)) > 0)
                return false;
        }
        return true;
    }

    private void inOrder(Node root, ArrayList<K> keys){
        if (root != null){
            inOrder(root.left, keys);
            keys.add(root.key);
            inOrder(root.right, keys);
        }
    }

    // 判断该二叉树是否是一颗平衡二叉树
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

    // 获取节点Node的高度
    private int getHeight(Node node){
        return node == null ? 0 : node.height;
    }

    // 获取节点Node的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node getNode(Node root, K key){
        if(root == null)
            return null;
        if(key.compareTo(root.key) < 0)
            return getNode(root.left, key);
        else if(key.compareTo(root.key) > 0)
            return getNode(root.right, key);
        else
            return root;
    }

    // 对节点node进行向右旋转操作，返回旋转后新的根节点x
    private Node rightRotate(Node node){
        Node newRoot = node.left;
        Node tempRight = newRoot.right;

        // 右旋操作
        newRoot.right = node;
        node.left = tempRight;

        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    // 对节点node进行向左旋转操作，返回旋转后新的根节点x
    private Node leftRotate(Node node){
        Node newRoot = node.right;
        Node tempLeft = newRoot.left;

        // 左旋操作
        newRoot.left = node;
        node.right = tempLeft;

        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        newRoot.height = Math.max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    // LR旋转
    private Node LR(Node node){
        Node newRoot = node.left.right;
        Node tempLeft = newRoot.left;
        Node tempRight = newRoot.right;
        newRoot.left = node.left;
        newRoot.right = node;
        newRoot.left.right = tempLeft;
        node.left = tempRight;
        return newRoot;
    }

    // RL旋转
    private Node RL(Node node){
        Node newRoot = node.right.left;
        Node tempLeft = newRoot.left;
        Node tempRight = newRoot.right;
        newRoot.right = node.right;
        newRoot.left = node;
        node.right = tempLeft;
        newRoot.right.left = tempRight;
        return newRoot;
    }

    public void add(K key, V value) {
        root = add_recursive(root, key, value);
    }

    // 递归插入
    // 返回插入结点后该树的根结点
    private Node add_recursive(Node node, K key, V value){
        // 递归出口
        if(node == null){
            size ++;
            return new Node(key, value);
        }
        if(key.compareTo(node.key) < 0)
            node.left = add_recursive(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add_recursive(node.right, key, value);
        else
            node.value = value;

        // 更新height
        node.height = 1 + Math.max(getHeight(node.left) , getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){ // 向左倾斜LL
            return rightRotate(node);
        } else if (balanceFactor < -1 && getBalanceFactor(node.right) <=0) { // 向右倾斜RR
            return leftRotate(node);
        } else if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) { // LR
            // return RL(node);
            // RL不平衡可以转换为先对不平衡节点的左子树进行左旋转，再对不平衡节点进行右旋转
            node.left = leftRotate(node.left);
            return rightRotate(node);
        } else if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // return RL(node);
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){
        if (node == null)
            return null;
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        }
        else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            } else {
                Node min = minimum(node.right);
//                min.right = removeMin(node.right);
                min.right = remove(node.right, min.key);
                min.left = node.left;
                node.left = null;
                node.right = null;
                retNode = min;
            }
        }

        if (retNode == null)
            return null;

        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left) , getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){ // 向左倾斜LL
            return rightRotate(retNode);
        } else if (balanceFactor < -1 && getBalanceFactor(retNode.right) <=0) { // 向右倾斜RR
            return leftRotate(retNode);
        } else if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) { // LR
            // return RL(node);
            // RL不平衡可以转换为先对不平衡节点的左子树进行左旋转，再对不平衡节点进行右旋转
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        } else if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            // return RL(node);
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node != null)
            node.value = newValue;
        else
            throw new IllegalArgumentException(key + " doesn't exist!");
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        AVLTree<Integer, Integer> avlTree = new AVLTree();
        Random random = new Random();
        int n = 10000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(100000));
        }

        for (int i = 0; i < n; i++) {
            int num = list.get(i);
            if (avlTree.contains(num))
                avlTree.set(num, avlTree.get(num)+1);
            else
                avlTree.add(num, 1);
        }

        System.out.println(avlTree.getSize());

        for (int num : list){
            avlTree.remove(num);
            if (!avlTree.isBST() || !avlTree.isBalanced())
                throw new RuntimeException("Error!");
        }
        System.out.println(avlTree.isBST());
        System.out.println(avlTree.isBalanced());
    }
}
