package Queue;

public class LoopQueue1<E> implements Queue{

    int front, tail;

    E[] data;

    public LoopQueue1(){
        this(10);
    }
    public LoopQueue1(int capacity){
        front = 0;
        tail = 0;
        data = (E[]) new Object[capacity+1];
    }

    public int getCapacity() {
        return data.length - 1 ;
    }

    @Override
    public int getSize() {
        return (tail - front + data.length) % data.length;
    }

    @Override
    public void enqueue(Object o) {
        if(isFull()){
            resize(getCapacity() * 2);
        }
        data[tail] = (E) o;
        tail = (tail+1) % data.length;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity+1];
        int size = getSize();
        for (int i = 0; i < size; i++) {
            newData[i] = data[front+i];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("Queue is empty!");
        }
        E e = data[front];
        front = (front + 1) % data.length;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("Queue is empty!");
        }
        return data[front];
    }

    public boolean isFull(){
        return (tail+1 % data.length) == front;
    }

    @Override
    public boolean isEmpty() {
        return front==tail;
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
