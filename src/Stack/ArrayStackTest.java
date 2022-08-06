package Stack;

public class ArrayStackTest<E> implements Stack{

    private Array<E> array;

    public ArrayStackTest(int capacity){
        array = new Array<E>(capacity);
    }

    public ArrayStackTest(){
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

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public void push(Object o) {
        array.addLast((E)o);
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
