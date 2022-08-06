package LinkedList;

public class LinkedList_1<E> {

    private class Node{

        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this.e = e;
            this.next = null;
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    public Node head;

    private int size;

    public LinkedList_1(){
        head = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public void addFirst(E e){
        add(e,0);
    }

    public void add(E e, int idx){
        if(idx<0 || idx>size){
            throw new IllegalArgumentException("Illegal add index");
        }
        Node p = head;
        for (int i = 0; i < idx; i++) {
            p = p.next;
        }
        p.next = new Node(e, p.next);
        size ++;
    }

    public void addLast(E e){
        add(e, size);
    }
}
