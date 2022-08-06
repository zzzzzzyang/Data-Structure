package LinkedList;

public class LinkedList_douCur<E>{
    private class Node<E> {
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    /* 带有虚拟头结点和尾结点
     * 相比于仅有头结点的优点在于: 插入尾部的时间复杂度为O(1)，但删除尾部的时间复杂度仍然是O(n)
     */
    private Node head, tail;
    private int size;

    public LinkedList_douCur(){
        head = new Node<E>();
        tail = head;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Node findPrec(int idx){
        Node p = head;
        for(int i=0;i<idx;i++)
            p = p.next;
        return p;
    }

    public Node findCur(int idx){
        Node p = head;
        for(int i=0; i<=idx; i++)
            p = p.next;
        return p;
    }

    public void add(E e, int idx){
        if (idx<0 || idx>size){
            throw new IllegalArgumentException("idx error");
        }
        if (idx != size) {
            Node p = findPrec(idx);
            p.next = new Node(e, p.next);
            size++;
        }else {
            addLast(e);
        }
    }

    public void addFirst(E e){
        add(e, 0);
    }

    public void addLast(E e){
        tail.next = new Node(e, null);
        tail = tail.next;
        size++;
    }

    public E get(int idx){
        if (idx<0 || idx>=size){
            throw new IllegalArgumentException("idx error");
        }
        Node cur = findCur(idx);
        return (E) cur.e;
    }

    public E getFirst(int idx){
        return get(0);
    }

    public E getLast(int idx){
        return (E) tail.e;
    }

    public E delete(int idx){
        if (idx<0 || idx>=size){
            throw new IllegalArgumentException("idx error");
        }
        Node p = findPrec(idx);
        Node u = p.next;
        p.next = u.next;
        u.next = null;

        size --;
        return (E) u.e;
    }

    public E deleteFirst(int idx){
        return delete(0);
    }

    public E deleteLast(int idx){
        return delete(size);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Node cur = head.next;
        while (cur != null){
            stringBuilder.append(cur.e + "-->");
            cur = cur.next;
        }
        stringBuilder.append("null");
        return stringBuilder.toString();
    }
}
