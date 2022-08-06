package LinkedList;

public class LinkedList<E> {

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

    private Node head;
    private int size;

    public LinkedList(){
        head = new Node(null, null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
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
            throw new IllegalArgumentException("Index error");
        }
        Node p = findPrec(idx);
        p.next = new Node(e, p.next);
        size++;
    }

    public void addFirst(E e){
        add(e, 0);
    }

    public void addLast(E e){
        add(e, size);
    }

    public E get(int idx){
        if (idx<0 || idx>=size){
            throw new IllegalArgumentException("idx error");
        }

        Node cur = findCur(idx);
        return (E) cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(E e, int idx){
        if (idx<0 || idx>=size){
            throw new IllegalArgumentException("idx error");
        }
        Node cur = findCur(idx);
        cur.e = e;
    }

    public boolean contains(E e){
        Node cur = head.next;
        while (cur != null){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
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

    public void traverse(){
        System.out.print("Head-->");
        Node p = head;
        while (p.next != null){
            p = p.next;
            System.out.print(p.e);
            if(p.next!=null){
                System.out.print("-->");
            }
        }
        System.out.println();
    }

    public E delete(int idx){
        if (idx<0 || idx>=size){
            throw new IllegalArgumentException("idx error");
        }
        Node p = findPrec(idx);
        Node u = p.next;
        p.next = u.next;
        u.next = null;
        size--;
        return (E) u.e;
    }

    public E deleteFirst(){
        return delete(0);
    }

    public E deleteLast(){
        return delete(size-1);
    }


    //头插法逆置
    public void reverse(){
        Node p = head.next;
        Node q;
        head.next = null;

        while(p!=null){
            q = p;
            p = p.next;

            q.next = head.next;
            head.next = q;
        }
    }


}
