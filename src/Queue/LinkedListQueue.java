package Queue;


// 无虚拟头结点
public class LinkedListQueue<E> implements Queue<E>{

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

    private Node head, tail;
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null){
            tail = new Node(e, null);
            head = tail;
        }else {
            tail.next = new Node(e, null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("Queue is empty!");
        }

        Node u = head;
        head = u.next;
        u.next = null;
        size--;

        if (head == null)
            tail = null;

        return (E) u.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }
        return (E) head.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front ");
        for (Node cur=head; cur!=null; cur = cur.next){
            sb.append(cur.e + "-->");
        }
        sb.append("Null tail");
        return sb.toString();
    }

    public static void main(String[] args) {

        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
