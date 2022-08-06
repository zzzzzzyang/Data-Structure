package Queue;

public class ArrayQueue<E> implements Queue{

    private Array array;

    public ArrayQueue(){
        array = new Array<E>();
    }

    public ArrayQueue(int capacity){
        array = new Array<E>(capacity);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public void enqueue(Object o) {
        array.addLast((E) o);
    }

    @Override
    public Object dequeue() {
        return array.removeFirst();
    }

    @Override
    public Object getFront() {
        return array.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: Front [");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if(i != array.getSize()-1){
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }
}
