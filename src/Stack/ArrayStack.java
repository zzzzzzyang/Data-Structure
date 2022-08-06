package Stack;

public class ArrayStack<E> implements Stack{

    Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<E>(capacity);
    }

    public ArrayStack(){
        array = new Array<E>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public void push(Object e) {
        array.addLast((E) e);
    }

    @Override
    public E peak() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if(i != array.getSize()-1){
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}

