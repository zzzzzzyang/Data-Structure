package Queue;

public class LoopQueueNoSize<E> implements Queue{

    private int front,tail;

    private E[] data;

    public LoopQueueNoSize(int capacity){
        front = 0;
        tail = 0;
        data = (E[])new Object[capacity+1];
    }

    public LoopQueueNoSize(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public int getSize() {
        return (tail - front + data.length) % data.length;
    }

    @Override
    public void enqueue(Object o) {
        if (isFull()){
            resize(getCapacity() * 2);
        }
        data[tail] = (E) o;
        tail = (tail+1) % data.length;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity+1];
        int size = getSize();
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front+i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("Down Overflow");
        }
        E e = data[front];
        front = (front+1) % data.length;

        if (getSize() == getCapacity()/4 && getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return e;
    }

    @Override
    public Object getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("Down Overflow");
        }
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    public boolean isFull(){
        return (tail + 1) % data.length == front;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i+1) % data.length){
            res.append(data[i]);
            if((i+1) % data.length != tail){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
