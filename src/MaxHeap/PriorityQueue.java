package MaxHeap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{

    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<E>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}