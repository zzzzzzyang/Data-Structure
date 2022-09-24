package Map;

import LinkedList.LinkedList;

public class LinkedListMap<K, V> implements Map<K, V>{

    private class Node<E> {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key, null, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if(cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node != null){
            node.value = value;
        }else {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size ++;
        }
    }

    @Override
    public V remove(K key) {

        Node pre = dummyHead, cur = dummyHead.next;
        while (cur != null){
            if(cur.key.equals(key)){
                Node delNode = cur;
                pre.next = cur.next;
                delNode.next = null;
                size --;
                return (V) delNode.value;
            }
            pre = cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : (V) node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node != null)
            node.value = newValue;
        else
            throw new IllegalArgumentException(key + " doesn't exist!");
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public V getOrDefault(K key, V defValue){
        Node node = getNode(key);
        if(node != null)
            return (V) node.value;
        else
            return defValue;
    }
}
