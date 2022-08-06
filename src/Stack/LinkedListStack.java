package Stack;

public class LinkedListStack<E> implements Stack<E>{

    private LinkedList<E> list;

    public LinkedListStack(){
        list = new LinkedList<E>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public E pop() {
        return list.deleteFirst();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E peak() {
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top ");
        sb.append(list);
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
        for (int i = 0; i <5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }
}
