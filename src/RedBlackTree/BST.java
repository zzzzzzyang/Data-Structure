package RedBlackTree;


public class BST<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
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
        if (key.compareTo(node.key) < 0)
            node.left = remove(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = remove(node.right, key);
        else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            } else {
                Node min = minimum(node.right);
                min.right = removeMin(node.right);
                min.left = node.left;
                node.left = null;
                node.right = null;
                return min;
            }
        }
        return node;
    }

    private Node minimum(Node node){
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除以node为根节点的二叉搜索树中的最小key值节点，返回新的根节点
    private Node removeMin(Node node){
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);

        return node;
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
}
