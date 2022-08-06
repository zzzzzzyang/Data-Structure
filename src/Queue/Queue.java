package Queue;

public interface Queue<E> {
    public int getSize();

    public void enqueue(E e);

    public E dequeue();

    public E getFront();

    public boolean isEmpty();
}
