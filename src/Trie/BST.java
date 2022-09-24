package Trie;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
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

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //非递归插入
    public void add(E e){
        if(root == null)
            root = new Node(e);
        else{
            Node p = root;
            Node parent = null;
            while(p != null){
                parent = p;
                if(e.compareTo(p.e) < 0)
                    p = p.left;
                else if(e.compareTo(p.e) > 0)
                    p = p.right;
                else
                    return;
            }
            if (e.compareTo(parent.e) < 0)
                parent.left = new Node(e);
            if (e.compareTo(parent.e) > 0)
                parent.right = new Node(e);
        }
        size ++;
    }

    public void add_(E e){
        root = add_recursive(root, e);
    }

    // 递归插入
    // 返回插入结点后该树的根结点
    private Node add_recursive(Node node, E e){
        // 递归出口
        if(node == null){
            size ++;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0)
            node.left = add_recursive(node.left, e);
        else if(e.compareTo(node.e) > 0)
            node.right = add_recursive(node.right, e);
        return node;
    }

    public E minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return minimum(root).e;
    }

    private Node minimum(Node node){
//        while(node.left != null)
//            node = node.left;
//        return node;
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    public E maximum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return maximum(root).e;
    }

    private Node maximum(Node node){
//        while(node.right != null)
//            node = node.right;
//        return node;
        if (node.right == null)
            return node;
        return maximum(node.right);
    }

    // 删除最小值节点
    public E removeMin(){
        E res = minimum();
        root = removeMin(root);
        return res;
    }

    // 删除以node为根节点的二叉搜索树中的最小值节点，返回新的根节点
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

    // 删除最大值节点
    public E removeMax(){
        E res = maximum();
        root = removeMax(root);
        return res;
    }

    private Node removeMax(Node node){
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);

        return node;
    }

    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0)
            node.left = remove(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = remove(node.right, e);
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

    // 迭代查询
    public boolean contains(E e){
        Node cur = root;
        while(cur != null){
            if (e.equals(cur.e))
                return true;
            else if (e.compareTo(cur.e) < 0){
                cur = cur.left;
            }else{
                cur = cur.right;
            }
        }
        return false;
    }

    // 递归查询
    public boolean contains_(E e){
        return contains_(root, e);
    }

    private boolean contains_(Node node, E e){
        if (node == null)
            return false;

        if (e.equals(node.e))
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains_(node.left, e);
        else
            return contains_(node.right, e);
    }

    // 先序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if (node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 先序遍历非递归1
    public void preOderNR1(){
        if (root == null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println(node.e);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    // 先序遍历非递归2
    public void preOrderNR2(){
        if (root == null)
            return;
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (p != null || !stack.isEmpty()){
            while(p != null){
                System.out.println(p.e);
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()){
                Node top = stack.pop();
                p = top.right;
            }
        }
    }

    // 中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void inOrderNR(){
        if(root == null)
            return;
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while (p != null || !stack.isEmpty()){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            Node top = stack.pop();
            System.out.println(top.e);
            p = top.right;
        }
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /*
     * 父节点在栈内会被访问两次，第一次从左子树出栈后访问父节点时，父节点不进行出栈，而是将右子树入栈，继续访问右子树。
     * 只有当右子树出栈后访问父节点时，父节点才会出栈。
     * 所以要记录上一个出栈的节点，用来判断一个节点的右子树是否被访问过。
     */
    public void postOrderNR(){
        if(root == null)
            return;
        Stack<Node> stack = new Stack<>();
        Node p = root;
        Node pre = null;
        while (p != null || !stack.isEmpty()){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            if (stack.peek().right != null && stack.peek().right != pre){
                p = stack.peek().right;
            } else {
                Node top = stack.pop();
                System.out.println(top.e);
                pre = top;
            }
        }
    }

    // 层序遍历
    public void levelOder(){
        if (root == null)
            return;
        Deque<Node> deque = new ArrayDeque<>();
        deque.push(root);
        while (!deque.isEmpty()){
            Node node = deque.poll();
            System.out.println(node.e);
            if (node.left != null)
                deque.add(node.left);
            if (node.right != null)
                deque.add(node.right);
        }
    }

    // 查找小于E的最大节点的值
    public E floor(E e){
        if (size == 0 || e.compareTo(minimum()) < 0)
            return null;
        return floor(root, e).e;
    }


    // 中序前驱
    // 返回以node为根的二叉搜索树中小于e的最大节点
    private Node floor(Node node, E e){
        if(node == null)
            return null;
        if(e.compareTo(node.e) == 0)
            return node;
        if(e.compareTo(node.e) < 0)
            return floor(node.left, e);
        Node temp = floor(node.right, e);
        return temp == null ? node : temp;
    }

    // 查找大于E的最小节点的值
    public E ceil(E e){
        if (size == 0 || e.compareTo(maximum()) > 0)
            return null;
        return ceil(root, e).e;
    }


    // 中序后继
    // 查找以node为根的二叉搜索树中大于e的最小节点
    private Node ceil(Node node, E e){
        if(node == null)
            return null;
        if(e.compareTo(node.e) == 0)
            return node;
        if(e.compareTo(node.e) > 0)
            return ceil(node.right, e);
        Node temp = ceil(node.left, e);
        return temp == null ? node : temp;
    }



    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder sb){
        if (node == null){
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }
        sb.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth+1, sb);
        generateBSTString(node.right, depth+1, sb);
    }

    private String generateDepthString(int depth){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }



}
