package Set;

public class BSTSet<E extends Comparable<E>> implements Set<E>{
    private BST<E> bst;

    public BSTSet(){
        bst = new BST<E>();
    }


    @Override
    public void add(E e) {
        bst.add_(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains_(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
