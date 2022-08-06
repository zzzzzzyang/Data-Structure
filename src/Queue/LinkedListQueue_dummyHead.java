package Queue;

// 带有虚拟头结点
public class LinkedListQueue_dummyHead<E> implements Queue<E>{

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

    private Node head, front, tail;
    private int size;

    public LinkedListQueue_dummyHead(){
        head = new Node<E>();
        front = head;
        tail = head;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(E e) {
        tail.next = new Node(e, null);
        tail = tail.next;
        size++;
        if(front==head)
            head = tail;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("empty");
        head.next = front.next;
        E e = (E) front.e;
        front = head.next;
        if (front == null)
            tail = null;
        return e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("empty");
        return (E) front.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front ");
        for (Node cur = front; cur!=null; cur = cur.next){
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
